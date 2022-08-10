package com.humber.bank.service;

import java.util.HashMap;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.humber.bank.entity.Branch;
import com.humber.bank.repository.BranchDao;

@Service
public class BranchServiceImpl implements BranchService{

	@Autowired
	BranchDao branchDao;

	@Override
	@Transactional
	public Map<String, Branch> addBranch(Branch branch) {
		Map<String, Branch> map = new HashMap<String, Branch>();
		branchDao.save(branch);
		map.put("Branch saved successfully", branch);
		return map;
	}

	
	
	
	
}
