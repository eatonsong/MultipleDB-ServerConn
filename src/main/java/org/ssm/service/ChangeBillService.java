package org.ssm.service;

import java.util.List;

import org.ssm.model.AppointDto;
import org.ssm.model.Bill;

public interface ChangeBillService {
	List testfind();
	String changeBill(Bill bi);
	String changeApp(AppointDto app);
	String deleteApp(AppointDto app);
}
