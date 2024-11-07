package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication // 스프링 부트로 만든 애플리케이션 시작 클래스임을 의미
public class BitcampSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(BitcampSpringbootApplication.class, args);
	/*	SpringApplication springApplication = new SpringApplication(BitcampSpringbootApplication.class);
		springApplication.setWebApplicationType(WebApplicationType.NONE);   // WAS 실행 못하게 함 -> 톰켓 실행 못함.
		springApplication.run(args)*/;
	}
}
/*
- 스프링 부트로 만든 애플리케이션은 일반 자바 애플리케이션으로 실행할 수도 있고
  웹 애플리케이션으로 실행할 수도 있다.
- 기본적으로 작성된 메인 클래스를 실행하면 웹 애플리케이션으로 실행된다.
  내장 Tomcat이 구동되고 브라우저에서 전송한 요청을 처리할 수 있다.
  하지만 코드를 수정하여 일반 자바 애플리케이션으로 실행하면 내장 Tomcat은 구동되지 않는다.


WebApplicationType 으로 설정할 수 있는 3가지 타입
① WebApplicationType.NONE – 웹으로 동작하지 않도록 설정
② WebApplicationType.SERVLET – 기존의 스프링 MVC를 기반으로 웹 애플리케이션을 구동하는 설정
③ WebApplicationType.REACTIVE – 스프링 5.0에서 추가된 비동기 처리와 논블로킹 입출력을 지원하는 웹플럭스(WebFlux)를 적용할 때 사용

외부 프로퍼티 사용
src/main/resources 의 application.properties 파일은 전체 프로젝트의 프로퍼티 정보를 관리하는 설정 파일이다.
자바 소스보다 application.properties 설정이 우선순위가 높다.
자바 소스에서 WebApplicationType.NONE 으로 설정했어도 프로퍼티 설정의 우선순위가 높기 때문에 웹 애플리케이션이 실행된다.

application.properties에 설정한 프로퍼티 정보들은 실제 해당 Properties 객체의 Setter 메소드가 호출되어 의존성이 주입된다는 것이다.
Ctrl 키를 누른 상태에서 server.port에 마우스를 대면 하이퍼링크로 변한다. 링크를 클릭하면 ServerProperties 클래스의 setPort() 메소드가 선택된다.

사용자가 정의한 클래스들이 자동으로 빈으로 등록되기 때문에 스프링 부트에서는 패키지 이름을 주의해서 작성해야 한다.
만약 루트 패키지인 "com.example.demo" 가 아닌 다른 패키지에 클래스를 작성하면 스프링 컨테이너는 해당 클래스를 빈으로 등록하지 않는다.
다른 패키지의 클래스까지 스캔 대상에 포함 시키려면 메인 클래스에 @ComponentScan을 추가하여 패키지를 직접 지정하면 된다.
*/