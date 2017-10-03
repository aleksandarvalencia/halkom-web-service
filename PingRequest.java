package wshalkom.model.requests;

/**
 * @author misa.stefanovic
 */

public class PingRequest {

	private String pingInput;	
	
	public PingRequest(String pingInput) {
		this.pingInput = pingInput;		
	}

    public String getPingInput()
    {
        return pingInput;
    }

    public void setPingInput(String pingInput)
    {
        this.pingInput = pingInput;
    }  

	
}
    