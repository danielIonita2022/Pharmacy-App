public class Customer {
    private String firstName, lastName;
    private String username;
    private String password;
    private int age;
    private String email;

    public int getAge() {
        return age;
    }
    public String getfirstName() {
        return firstName;
    }
    public String getlastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public String getEmail(){return email;}
    public void setAge(int age) {
        this.age = age;
    }

    public void setlastName(String lastName) {
        this.lastName = lastName;
    }


    public void setfirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public void setEmail(String email){this.email = email;}
}
