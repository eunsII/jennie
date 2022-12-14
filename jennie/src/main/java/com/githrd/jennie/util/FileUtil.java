package com.githrd.jennie.util;

import java.io.*;
import java.util.*;

import javax.servlet.http.*;

import com.oreilly.servlet.*;
import com.oreilly.servlet.multipart.*;

import com.githrd.jennie.vo.*;

/**
 * 이 클래스는 파일업로드에 필요한 기능을 처리하고
 * 업로드 파일의 정보를 만들어주는 기능의 유틸리티적인 클래스
 * @author	전은석
 * @since	2022.05.23
 * 
 * 			작업이력 ]
 * 				2022.05.23	- 담당자 : 전은석
 * 							- 클래스 제작
 * 		
 *
 */
public class FileUtil {
	private ArrayList<FileVO> list;
	private HttpServletRequest req;
	private MultipartRequest multi;
	private String dir, path, bPath;
	
	public FileUtil() {}
	public FileUtil(HttpServletRequest req, String dir) {
		this.req = req;
		this.dir = dir;
		setMulti();
	}
	
	// MultipartRequest 셋팅함수
	public void setMulti() {
		path = this.getClass().getResource("/").getPath();
		path = path.substring(0, path.lastIndexOf("/WEB-INF")) + dir;
		
		try {
			multi = new MultipartRequest(req, path, 1024 * 1024 * 10, "UTF-8", new FileRenamePolicy() {

				@Override
				public File rename(File file) {
					// 업로드하는 파일이 겹칠경우 호출되는 함수
					// 우리의 규칙  파일이름_숫자.확장자
					String filename = file.getName(); // 파일이름추출함수
					String newname = filename;
					String name = filename.substring(0, filename.lastIndexOf("."));
					String ext = filename.substring(filename.lastIndexOf("."));
					int count = 0;
					File f = new File(path, newname);
					// ==> 새로운 이름의 파일이 존재하는지 검사하고
					// 		존재하면 카운트를 증가시킨 새로운 이름으로 파일을 다시 만들어서
					//		그 파일이 존재하는 검사하고....
					while(f.exists()) {
						// 이 경우는 이미 파일이 있는 경우이므로
						// 카운트를 증가시켜서 파일을 다시 만든다.
						++count;
						newname = name + "_" + count + ext;
						
						f = new File(path, newname);
					}
					
					return f;
				}
				
			});
			
			/*
				MultipartRequest 생성자
				
					MultipartRequest(HttpServletRequest req, 저장경로, 업로드가능크기, 
														파일이름이코딩방식, 파일리네임정책);
			 */
			setList();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	// 파일 정보 리스트 셋팅함수
	public void setList() {
		list = new ArrayList<FileVO>();
		
		// 전송된 파일데이터의 키값만 뽑아본다.
		Enumeration<String> en = multi.getFileNames(); // 전송된파일들의 키값들만 추출...
		while(en.hasMoreElements()) {
			String key = en.nextElement();
			String oriname = multi.getOriginalFileName(key); // 업로드란 원래이름...
			System.out.println("############# oriname : " + oriname);
			if(oriname == null) {
				continue;
			}
			String savename = multi.getFilesystemName(key); // 업로드된 저장이름....
			File file = multi.getFile(key);					// 파일 추출...
			long len = file.length();						// 파일 사이즈
			
			// VO 만들고
			FileVO fVO = new FileVO();
			fVO.setOriname(oriname);
			fVO.setSavename(savename);
			fVO.setDir(dir);
			fVO.setLen(len);
			
			list.add(fVO);
			
			saveBackup(file);
		}
	}
	
	// 업로드파일 작업폴더로 저장해주는 함수
	public void saveBackup(File file) {
		// 작업 경로를 만든다.
		bPath = this.getClass().getResource("/").getPath();
		bPath = bPath.substring(0, bPath.indexOf("/source")) + 
							"/git/blackpink/jennie/src/main/webapp" + dir;
		File devFile = new File(bPath, file.getName());
		FileInputStream fin = null;
		PrintStream ps = null;
		try {
			byte[] buff = new byte[10240];
			fin = new FileInputStream(file);
			ps = new PrintStream(devFile);
			
			while(true) {
				int len = fin.read(buff);
				if(len == -1) break; // 읽은 데이터가 없는 경우
				ps.write(buff, 0, len);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
