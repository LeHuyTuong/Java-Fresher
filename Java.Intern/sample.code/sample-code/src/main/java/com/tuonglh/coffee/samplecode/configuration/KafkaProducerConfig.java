package com.tuonglh.coffee.samplecode.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

/*
 * Cấu hình Kafka Producer cho ứng dụng.
 *
 * Kafka là gì?
 * - Kafka là hệ thống message queue phân tán, cho phép gửi/nhận message giữa các service
 * - Ví dụ: User đăng ký -> đẩy message vào Kafka -> service khác gửi email (async)
 *
 * Các khái niệm cơ bản:
 * - Producer: Service gửi message vào Kafka (file này config cho Producer)
 * - Consumer: Service nhận message từ Kafka
 * - Topic: "Kênh" chứa message, giống như table trong database
 * - Broker: Kafka server, nơi lưu trữ message
 */
@Configuration
public class KafkaProducerConfig {

    /*
     * Địa chỉ Kafka broker (server)
     * - Format: host:port (ví dụ: localhost:9092)
     * - Production thường có nhiều broker: "broker1:9092,broker2:9092,broker3:9092"
     * - Port 29092 thường dùng khi chạy Kafka trong Docker
     */
    @Value("${spring.kafka.bootstrap-servers:localhost:29092}")
    private String bootstrapServers;

    /*
     * Tạo ProducerFactory - "nhà máy" sản xuất Kafka Producer
     *
     * Tại sao cần Serializer?
     * - Kafka truyền data dưới dạng bytes
     * - StringSerializer chuyển String thành bytes để gửi qua network
     * - Nếu muốn gửi Object (JSON), dùng JsonSerializer thay thế
     */
    @Bean
    public ProducerFactory<String, String> producerFactory() {
        Map<String, Object> config = new HashMap<>();

        // Địa chỉ Kafka broker để kết nối
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);

        // Serializer cho KEY - dùng để phân chia message vào partition
        // Cùng key sẽ vào cùng partition -> đảm bảo thứ tự message
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        // Serializer cho VALUE - nội dung chính của message
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        /*
         * TODO: [Production] Thêm các config sau để đảm bảo độ tin cậy:
         *
         * config.put(ProducerConfig.ACKS_CONFIG, "all");
         * -> Đợi TẤT CẢ replica xác nhận đã lưu message
         * -> Chậm hơn nhưng không mất data (quan trọng cho banking/fintech)
         *
         * config.put(ProducerConfig.RETRIES_CONFIG, 3);
         * -> Tự động retry 3 lần nếu gửi thất bại
         *
         * config.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, true);
         * -> Tránh gửi trùng message khi retry
         */

        return new DefaultKafkaProducerFactory<>(config);
    }

    /*
     * Tạo KafkaTemplate - công cụ chính để gửi message
     *
     * Cách sử dụng:
     * 
     * @Autowired
     * private KafkaTemplate<String, String> kafkaTemplate;
     *
     * // Gửi message đơn giản
     * kafkaTemplate.send("topic-name", "Hello Kafka!");
     *
     * // Gửi với key (đảm bảo thứ tự cho cùng key)
     * kafkaTemplate.send("topic-name", "user-123", "User registered");
     */
    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    /*
     * Tự động tạo topic "confirm-account-topic" khi ứng dụng khởi động
     *
     * Giải thích parameters:
     * - name: Tên topic, giống như tên table
     *
     * - partitions = 3:
     * + Topic được chia thành 3 phần độc lập
     * + Cho phép 3 consumer xử lý song song -> tăng throughput
     * + Rule: partitions >= số consumer trong cùng group
     *
     * - replicationFactor = 1:
     * + Số bản sao của mỗi partition trên các broker khác nhau
     * + = 1: Chỉ có 1 bản (DEV only, mất data nếu broker chết)
     * + = 3: Production standard, chịu được 2 broker chết
     *
     * Lưu ý Production:
     * - Nên cấu hình qua application.yml, không hardcode
     * - replicationFactor phải <= số broker trong cluster
     * - Topic đã tồn tại sẽ không bị ghi đè
     */
    @Bean
    public NewTopic confirmAccountTopic() {
        // TODO: Externalize config ra application.yml cho production
        int partitions = 3;
        short replicationFactor = 1; // Tăng lên 3 cho production

        return new NewTopic("confirm-account-topic", partitions, replicationFactor);
    }
}
