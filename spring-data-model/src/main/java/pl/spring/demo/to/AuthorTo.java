package pl.spring.demo.to;

public class AuthorTo implements IdAware{
	private Long id;
    private String firstName;
    private String lastName;

    public AuthorTo() {
    }

    public AuthorTo(Long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public boolean findByFullName (String name) {
    	if (firstName != null && lastName != null) {
    		String fullName = new String(firstName + " " + lastName);
    		return fullName.regionMatches(true, 0, name, 0, name.length());
    	}
    	return false;
    }
    
    public boolean findByFirstName (String name) {
    	if (firstName != null) {
    		return firstName.regionMatches(true, 0, name, 0, name.length());
    	}
    	return false;
    }
    
    public boolean findByLastName (String name) {
    	if (lastName != null) {
    		return lastName.regionMatches(true, 0, name, 0, name.length());
    	}
    	return false;
    }
}
