package requestandresponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"name", "job"})
public class PutApiRequest {

	private String name ;
	private String job ;
	
	public PutApiRequest()
	{
		this.name = "" ;
		this.job  = "" ;
	}
	
	@JsonProperty("name")
    public String getname() {return name;}
    
    public void setname(String name) {this.name = name;}
    
    @JsonProperty("job")
    public String getjob() {return job;}
    
    public void setjob(String job) {this.job = job;}
}
