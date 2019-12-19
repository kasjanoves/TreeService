package treeservice.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class TreeNode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long weight;
    @ManyToOne
    @JoinColumn(name = "parent_id")
    private TreeNode parent;
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<TreeNode> subNodes = new ArrayList<TreeNode>(0);

    public TreeNode() {
	super();
    }

    public TreeNode(Long weight) {
	super();
	this.weight = weight;
    }

    public Long getWeight() {
	return weight;
    }

    public void setWeight(Long weight) {
	this.weight = weight;
    }

    public TreeNode getParent() {
	return parent;
    }

    public void setParent(TreeNode parent) {
	this.parent = parent;
    }

    public List<TreeNode> getSubNodes() {
	return Collections.unmodifiableList(subNodes);
    }

    public TreeNode addNode(TreeNode node) {
	subNodes.add(node);
	if (node.getParent() != null)
	    node.getParent().removeNode(node);
	node.setParent(this);
	return this;
    }

    public void removeNode(TreeNode node) {
	subNodes.remove(node);
	node.setParent(null);
    }

}
