package treeservice.domain;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class CalcRequestResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "root_id")
    private TreeNode root;
    private LocalDateTime requestTime;
    private Long result;

    public CalcRequestResult() {
	super();
    }

    public CalcRequestResult(TreeNode root, LocalDateTime requestTime, Long sum) {
	super();
	this.root = root;
	this.requestTime = requestTime;
	this.result = sum;
    }

    public LocalDateTime getRequestTime() {
	return requestTime;
    }

    public void setRequestTime(LocalDateTime requestTime) {
	this.requestTime = requestTime;
    }

    public Long getResult() {
	return result;
    }

    public void setResult(Long result) {
	this.result = result;
    }

    public TreeNode getRoot() {
	return root;
    }

}
