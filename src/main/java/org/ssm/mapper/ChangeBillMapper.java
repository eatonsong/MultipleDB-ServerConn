package org.ssm.mapper;

import java.util.List;

import org.ssm.model.AppointDto;
import org.ssm.model.Bill;

public interface ChangeBillMapper {
	List testfind();
	int changeBill(Bill bi);
	int insertApp(AppointDto app);
	int deleteApp(AppointDto app);
}
