package com.tuonglh.coffee.samplecode.repository;

import com.tuonglh.coffee.samplecode.dto.response.PageResponse;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component // là 1 bean , được khởi tạo khi ứng dụng combine , hoặc @Repository
public class SearchRepository {

    @PersistenceContext
    private EntityManager em;

    public PageResponse<?> getALlUserWithSortByColumnAndSearch(int pageNo, int pageSize, String search, String sortBy) {
        //B1 query list user

        // đây là cách sử dụng thằng alias, là mật danh
        StringBuilder sqlQuery = new StringBuilder("select new com.tuonglh.coffee.samplecode.dto.response.UserDetailResponse(u.id, u.firstName, u.lastName, u.email, u.phone)" +
                " from User u where 1=1"); // chi ro package
        if(StringUtils.hasLength(search)){
            sqlQuery.append(" and (lower(u.firstName) like lower(:firstName)")  // truyen vao fiend cua entity
                    .append(" or lower(u.lastName) like lower(:lastName)")
                    .append(" or lower(u.email) like lower(:email))");
        }

        if(StringUtils.hasLength(sortBy)){
                //firstName : asc|desc
            Pattern pattern = Pattern.compile("(\\w+)[,:](asc|desc)?", Pattern.CASE_INSENSITIVE);
            // có 3 group
                Matcher matcher = pattern.matcher(sortBy.trim());

                if(matcher.find()){
                    //  xử lý đk sort
                    String col = matcher.group(1);
                    String dir = matcher.group(2) == null ? "asc" : matcher.group(2).toLowerCase();
                    sqlQuery.append(String.format(" order by u.%s %s",col, dir)); // find thì truyền vào thôi
                }
        }

        Query selectQuery = em.createQuery(sqlQuery.toString());

        if(StringUtils.hasLength(search)){
            String kw = "%" +  search + "%";
            selectQuery.setParameter("firstName",  kw); // với spring boot 2 thì sẽ cần thêm : trước param
            selectQuery.setParameter("lastName", kw);
            selectQuery.setParameter("email" , kw);
        }



        selectQuery.setFirstResult((pageNo)); // vị trí của records. of set trong câu lệnh query, setFirstResult sẽ là 100 , là pageN
        selectQuery.setMaxResults(pageSize);// truyền vào max records


        List<?> users = selectQuery.getResultList();

        //B2 Query record
        // sử dụng theo số vị trí
        StringBuilder sqlCountQuery = new StringBuilder("select count(*) from User u where 1=1");
        if(StringUtils.hasLength(search)){
            sqlCountQuery.append(" and (lower(u.firstName) like lower(?1)") // tryen fiend cua entity
                    .append(" or lower(u.lastName) like lower(?2)")
                    .append(" or lower(u.email) like lower(?3))");
        }
        Query selectCountQuery = em.createQuery(sqlCountQuery.toString());

        if(StringUtils.hasLength(search)){
            String kw = "%" +  search + "%";
            selectCountQuery.setParameter(1,  kw);
            selectCountQuery.setParameter(2, kw);
            selectCountQuery.setParameter(3 , kw);
        }

        Long totalElements = (Long) selectCountQuery.getSingleResult(); // in ra 1 gia tri
        System.out.println(totalElements);

        Page<?> page = new PageImpl<>(users, PageRequest.of(pageNo , pageSize), totalElements);


        return PageResponse.builder()
                .pageNo(pageNo)
                .pageSize(pageSize)
                .totalPage(page.getTotalPages()) // kiểu long nên cần parseInt
                .items(page.stream().toList())
                .build();
    }
}
