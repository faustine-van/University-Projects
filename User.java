// add Java Scanner Class
// add database connection
// isLogin
class User{
    private String username;
    private String email;
    private String password;
    // private boolean isLogin;
    
    User(String name, String email, String pass){
        this.username = name;
        this.email = email;
        this.password = pass;
    }
    String getPassword(){
        return password;
    }
    void signUp(){
        System.out.println("User " + username + " created successfully");
    }
    
    class Authentication {
        void login(String username, String pass){
            if(pass.equals(getPassword())){
                System.out.println("Login successful");
            } else{
                System.out.println("Login failed");
            }
            
        }
    }
    
}
class Main {
    public static void main(String[] args){
        User user = new User("faustine", "faustine@gmail.com", "12345");
        user.signUp();
        User.Authentication u = user.new Authentication();
        u.login("faustine", "12345");
    }
}
// Inner classes can access the outer class’s private members — good encapsulation.