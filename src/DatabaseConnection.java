public abstract class DatabaseConnection {
    // Fields
    protected String url;
    protected String username;
    protected String password;

    // Abstract methods
    public abstract void connect();
    public abstract void disconnect();
    public abstract boolean isConnected();

    // Concrete methods
    public void setUrl(String url) {
        this.url = url;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Getter methods
    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
