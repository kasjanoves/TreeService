package treeservice.service;

import org.springframework.stereotype.Component;

import treeservice.domain.CalcRequestResult;
import treeservice.domain.TreeNode;

@Component
public class CalcServiceImpl implements CalcService {

    @Override
    public void calcSum(CalcRequestResult result) {

	calcRecursively(result.getRoot(), result);

    }

    private void calcRecursively(TreeNode root, CalcRequestResult result) {
	result.setResult(result.getResult() + root.getWeight());
	for (TreeNode sub : root.getSubNodes())
	    calcRecursively(sub, result);
    }

}
