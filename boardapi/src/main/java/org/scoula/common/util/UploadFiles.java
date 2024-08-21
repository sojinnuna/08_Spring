package org.scoula.common.util;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;

public class UploadFiles {
    public static String upload(String baseDir, MultipartFile part) throws IOException {
        // 기본 디렉토리가 있는지 확인, 없으면 새로 생성
        File base = new File(baseDir);
        if(!base.exists()) {
            base.mkdirs(); // 중간에 존재하지 않는 디렉토리까지 모두 생성
        }

        String fileName = part.getOriginalFilename(); // 원본파일명 저장
//        base 디렉토리 내에 고유한 이름을 가지는 파일 생성
        File dest = new File(baseDir, UploadFileName.getUniqueName(fileName));
        part.transferTo(dest); // 지정한 경로로 업로드 파일 이동
        return dest.getPath(); // 저장된 파일 경로 리턴
    }
// 파일 크기를 사람이 읽기 쉬운 형식으로 변환
// 1,225,957 바이트 -> "1.2MB"
    public static String getFormatSize(Long size) {
        if (size <= 0)
            return "0";
        
        final String[] units = new String[] { "Bytes", "KB", "MB", "GB", "TB" }; // log 계산해서 알아냄
        int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
        return new DecimalFormat("#,##0.#").format(size / Math.pow(1024, digitGroups)) + " " + units[digitGroups];
    }

//    파일의 다운로드를 처리해주는 메소드
    public static void download(HttpServletResponse response, File file, String orgName) throws Exception {
// response의 contentType을 다운로드 파일로 설정
        response.setContentType("application/download");
//   파일의 크기를 response에 설정
        response.setContentLength((int)file.length());

//        한글 파일명을 URL 인코딩(필수)
        String filename = URLEncoder.encode(orgName, "UTF-8"); // 한글 파일명인 경우 인코딩 필수
//        response 헤더에 파일 다운로드 정보 설정
        response.setHeader("Content-disposition", "attachment;filename=\"" + filename + "\"");

//        response의 형태를 알 수 없기 때문에 OutputStream 사용
        try (OutputStream os = response.getOutputStream();
             BufferedOutputStream bos = new BufferedOutputStream(os)) {
//           원본 파일을 스트림으로 전송(복사)
            Files.copy(Paths.get(file.getPath()), bos);
        }
    }
}