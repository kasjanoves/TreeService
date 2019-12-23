package treeservice.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
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
import javax.persistence.OrderColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table
public class TreeNode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long weight;
    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id", insertable = false, updatable = false)
    @JsonIgnore
    private TreeNode parent;
    @OrderColumn(name = "position")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "parent_id")
    private List<TreeNode> subNodes = new ArrayList<TreeNode>(0);

    public TreeNode() {
	super();
    }

    public TreeNode(Long weight) {
	super();
	this.weight = weight;
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
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
	node.setParent(this);
	return this;
    }

    public void removeNode(TreeNode node) {
	subNodes.remove(node);
	node.setParent(null);
    }

    @Override
    public boolean equals(Object obj) {
	if (!(obj instanceof TreeNode))
	    return false;
	if (((TreeNode) obj).getId() != null && id != null) {
	    boolean byIds = id.equals(((TreeNode) obj).getId());
	    return deepEquals(byIds, (TreeNode) obj);
	}
	if (((TreeNode) obj).getWeight() != null && weight != null) {
	    boolean byWeight = weight.equals(((TreeNode) obj).getWeight());
	    return deepEquals(byWeight, (TreeNode) obj);
	}
	return super.equals(obj);
    }

    private boolean deepEquals(boolean byIds, TreeNode obj) {
	if (subNodes.size() != ((TreeNode) obj).getSubNodes().size())
	    return false;
	Iterator<TreeNode> subIterator = obj.getSubNodes().iterator();
	for (TreeNode sub1 : subNodes) {
	    if (subIterator.hasNext()) {
		byIds = byIds && sub1.equals(subIterator.next());
	    }
	}
	return byIds;
    }

    @Override
    public int hashCode() {
	if (id != null)
	    return id.intValue();
	return super.hashCode();
    }

    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder();
	sb.append("TreeNode(" + id + "," + weight + ")");
	sb.append(subNodes.toString());
	return sb.toString();
    }

}
