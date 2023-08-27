package requestandresponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"name", "job" , "id" , "createdAt"})
public class PostApiResponse {
	
	private String name ;
	private String job ;
	private String id ;
	private String createdAt ;
	
	@JsonProperty("name")
    public String getname() {return name;}
	
	@JsonProperty("job")
    public String getjob() {return job;}
	
	@JsonProperty("id")
    public String getid() {return id;}
	
	@JsonProperty("createdAt")
    public String getcreatedAt() {return createdAt;}

}
