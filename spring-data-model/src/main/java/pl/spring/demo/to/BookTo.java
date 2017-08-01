package pl.spring.demo.to;

import java.util.ArrayList;
import java.util.List;

public class BookTo implements IdAware {
    private Long id;
    private String title;
    private List<AuthorTo> authors;

    public BookTo() {
    }

    public BookTo(Long id, String title, String authorFirstName, String authorLastName) {
        this.id = id;
        this.title = title;
        this.authors = new ArrayList<AuthorTo>();
        this.authors.add(new AuthorTo(1L, authorFirstName, authorLastName));
    }

    @Override
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<AuthorTo> getAuthors() {
        return authors;
    }

    public void setAuthors(List<AuthorTo> authors) {
        this.authors = authors;
    }
    
    public boolean findByTitle (String title) {
    	if (title != null) {
			return getTitle().regionMatches(true, 0, title, 0, title.length());
		}
    	return false;
    }
    
    public boolean findByAuthor (String toFind) {
    	if (authors != null) {
    		for (AuthorTo author : authors) {
    			return (author.findByFullName(toFind) || author.findByFirstName(toFind) || author.findByLastName(toFind));
    		}
		}
    	return false;
    }
}
