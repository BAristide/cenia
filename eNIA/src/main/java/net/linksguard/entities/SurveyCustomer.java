package net.linksguard.entities;

 
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob; 
@Entity
public class SurveyCustomer {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private int qest1answ;
	private int qest2answ; 
	private long issueId;
	@Lob
	private String answer_text;
	public SurveyCustomer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SurveyCustomer(int qest1answ, int qest2answ, long issueId, String answer_text) {
		super();
		this.qest1answ = qest1answ;
		this.qest2answ = qest2answ;
		this.issueId = issueId;
		this.answer_text = answer_text;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getQest1answ() {
		return qest1answ;
	}
	public void setQest1answ(int qest1answ) {
		this.qest1answ = qest1answ;
	}
	public int getQest2answ() {
		return qest2answ;
	}
	public void setQest2answ(int qest2answ) {
		this.qest2answ = qest2answ;
	}
	public long getIssueId() {
		return issueId;
	}
	public void setIssueId(long issueId) {
		this.issueId = issueId;
	}
	public String getAnswer_text() {
		return answer_text;
	}
	public void setAnswer_text(String answer_text) {
		this.answer_text = answer_text;
	}
	 
	 
	
	

}
