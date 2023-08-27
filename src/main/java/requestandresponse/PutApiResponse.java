package requestandresponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"name", "job", "updatedAt"})
public class PutApiResponse {

	private String name ;
	private String job ;
	private String updatedAt ;
	
	@JsonProperty("name")
    public String getname() {return name;}
	
	@JsonProperty("job")
    public String getjob() {return job;}
	
    @JsonProperty("updatedAt")
    public String getupdatedAt() {return updatedAt;}
}
