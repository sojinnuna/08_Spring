package org.scoula.common.util;
public class UploadFileName {
    public static String getUniqueName(String filename) {

//        lastIndexOf(문자열): 뒤에서부터 해당 문자열을 찾아서 처음 등장하는 인덱스 반환
        int ix = filename.lastIndexOf("."); // 파일명에서 확장자 위치 추출
//        subString(시작인덱스, 끝인덱스+1): 시작인덱스부터 끝인덱스까지 문자열ㅇㄹ 잘라냄
        String name = filename.substring(0, ix); // 파일명 추출
//        subString에 시작인덱스만 있을 경우엔 시작인덱스부터 끝까지 잘라낸다
        String ext =  filename.substring(ix+1); // 확장명 추출

//        파일명 뒤에 타임스탬프를 붙여서 고유한 파일명으로 만들어줌
        return String.format("%s-%d.%s", name, System.currentTimeMillis(), ext); // 파일명에
    }
}