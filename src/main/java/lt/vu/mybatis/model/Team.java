package lt.vu.mybatis.model;

public class Team {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.TEAM.ID
     *
     * @mbg.generated Sat Apr 10 22:46:56 EEST 2021
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.TEAM.NAME
     *
     * @mbg.generated Sat Apr 10 22:46:56 EEST 2021
     */
    private String name;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUBLIC.TEAM.ID
     *
     * @return the value of PUBLIC.TEAM.ID
     *
     * @mbg.generated Sat Apr 10 22:46:56 EEST 2021
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUBLIC.TEAM.ID
     *
     * @param id the value for PUBLIC.TEAM.ID
     *
     * @mbg.generated Sat Apr 10 22:46:56 EEST 2021
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUBLIC.TEAM.NAME
     *
     * @return the value of PUBLIC.TEAM.NAME
     *
     * @mbg.generated Sat Apr 10 22:46:56 EEST 2021
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUBLIC.TEAM.NAME
     *
     * @param name the value for PUBLIC.TEAM.NAME
     *
     * @mbg.generated Sat Apr 10 22:46:56 EEST 2021
     */
    public void setName(String name) {
        this.name = name;
    }
}