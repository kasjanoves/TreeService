package treeservice.service;

import org.springframework.stereotype.Component;

import treeservice.domain.TreeNode;

@Component
public class CalcServiceImpl implements CalcService {

    @Override
    public Long calcSum(TreeNode root) {

	Long result = 0L;
	calcRecursively(root, result);
	return result;
    }

    private void calcRecursively(TreeNode root, Long resultAccum) {
	resultAccum += root.getWeight();
	for (TreeNode sub : root.getSubNodes())
	    calcRecursively(sub, resultAccum);
    }

}
