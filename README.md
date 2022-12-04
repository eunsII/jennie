# jennie
<img src="https://github.com/eunsII/jennie/blob/main/doc/main.png?raw=true" width="700px" height="auto">

<h3>Dispatch Servlet 클래스 구현해서 다이나믹 웹을 구현하는 방법</h3>

<h4>작업순서</h4>

1. 요청설계를 한다.
<pre>
    [ 요청 설계 형식 ]
      요청내용 : /blackpink/member/login.blp
      파라미터 : 무
      컨트롤러 : com.blackpink.jennie.controller.Login
      뷰     : /WEB-INF/views/member/loign.jsp
</pre>
      
      의 형식으로 각 기능마다 먼저 요청 설계를 하고 진행해야하고
      엑셀이나 메모장에 작성해서 작업을 진행하는 것이 좋습니다.

<h5>***. doc폴더의 jennie요청설계.txt 파일 참고<h5>

2. jsp 파일을 만든다.
3. 컨트롤러 클래스를 만든다.(인터페이스를 상속받아서 제작)
4. 요청 매핑을 한다. ( /src/main/java/com/githrd/jennie/resources/blp.properties 에 작성 )
5. 요청 처리 코딩을 한다.

의 순서로 진행하면 된다.
