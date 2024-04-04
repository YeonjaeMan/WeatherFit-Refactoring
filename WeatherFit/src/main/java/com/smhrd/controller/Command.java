package com.smhrd.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// .do 로 들어오는 요청 처리하는 인터페이스
public interface Command {

   public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, InterruptedException;
   
}