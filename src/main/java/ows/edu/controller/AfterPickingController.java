package ows.edu.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;
import ows.edu.dto.ReleaseInspection;
import ows.edu.service.ReleaseInspectionService;

@RestController
@RequestMapping("/afterPicking")
@Log4j2
public class AfterPickingController {

	@Resource
	ReleaseInspectionService releaseInspectionService;
	
}
