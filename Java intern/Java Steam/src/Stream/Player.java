package Stream;

class Player {
    String name;
    String nationality;
    int goals;

    public Player(String name, String nationality, int goals) {
        this.name = name;
        this.nationality = nationality;
        this.goals = goals;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public int getGoals() {
        return goals;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    @Override
    public String toString() {
        return name + " - " + nationality + " - " + goals;
    }
}
