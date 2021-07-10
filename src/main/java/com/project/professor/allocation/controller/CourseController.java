package com.project.professor.allocation.controller;

import com.project.professor.allocation.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/courses")
public class CourseController {

    private final CourseService courseService;

}
