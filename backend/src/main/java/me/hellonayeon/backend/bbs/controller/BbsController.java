package me.hellonayeon.backend.bbs.controller;

import java.util.Date;
import me.hellonayeon.backend.bbs.dto.request.BbsListRequest;
import me.hellonayeon.backend.bbs.dto.request.CreateBbsRequest;
import me.hellonayeon.backend.bbs.dto.request.UpdateBbsRequest;
import me.hellonayeon.backend.bbs.dto.response.BbsListResponse;
import me.hellonayeon.backend.bbs.dto.response.BbsResponse;
import me.hellonayeon.backend.bbs.dto.response.CreateBbsResponse;
import me.hellonayeon.backend.bbs.dto.response.DeleteBbsResponse;
import me.hellonayeon.backend.bbs.dto.response.UpdateBbsResponse;
import me.hellonayeon.backend.bbs.service.BbsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController         // RequestBody : Client에서 JSON를 받아서 Java의 객체에 넣음  , 
						// ResponseBody : Server의 객체를 JSON 형태로 변환해서 Client 에게 전송 
@RequestMapping("/bbs")
@Api(tags= {"BbsController : 게시판 API 정보를 제공하는 Controller"})
public class BbsController {

	// API 문서 출력 URL : http://localhost:3000/swagger-ui/index.html
	
	// DI : 생성자 주입 , 
	private final BbsService service;

	public BbsController(BbsService service) {
		this.service = service;
	}

	/* [GET /bbs?choice={choice}&search={search}&page={page}] 게시글 목록 API */
	
	@ApiOperation(value=" getBbsList() : 게시판의 List 정보를 출력해 주는 API ")
	@GetMapping
	public ResponseEntity<BbsListResponse> getBbsList(@ModelAttribute BbsListRequest req){
		System.out.println("BbsController getBbsList() " + new Date());
		
		return ResponseEntity.ok(service.getBbsList(req));
	}

	/* [GET /bbs/{seq}?readerId={id}] 게시글 상세 API */
	@ApiOperation(value=" getBbs() : 게시판의 상세 정보를 출력해 주는 API ") 
	@GetMapping("/{seq}")
	public ResponseEntity<BbsResponse> getBbs(@PathVariable  Integer seq, @RequestParam String readerId) {
		System.out.println("BbsController getBbs() " + new Date());

		return ResponseEntity.ok(service.getBbs(seq, readerId));
	}

	/* [POST] /bbs 게시글 작성 */
	@ApiOperation(value=" createBbs() :  게시판의 새글을 작성해 주는 API ") 
	@PostMapping
	public ResponseEntity<CreateBbsResponse> createBbs(@RequestBody CreateBbsRequest req) {
		System.out.println("BbsController createBbs " + new Date());
		
		return ResponseEntity.ok(service.createBbs(req));
	}

	/* [POST] /bbs/{parentSeq}/answer 게시글 답글 작성  */
	@ApiOperation(value=" createBbsAnswer() :   게시판의 답글을 작성하는 API ")
	@PostMapping("/{parentSeq}/answer")
	public ResponseEntity<CreateBbsResponse> createBbsAnswer(@PathVariable Integer parentSeq, @RequestBody CreateBbsRequest req) {
		System.out.println("BbsController createBbsAnswer " + new Date());

		return ResponseEntity.ok(service.createBbsAnswer(parentSeq, req));
	}

	/* [PATCH] /bbs/{seq} 게시글 수정  */
	// TODO - 수정하는 사람 ID 확인
	@ApiOperation(value=" updateBbs() :   게시판의 게시글을 수정하는 API ")
	@PatchMapping("/{seq}")
	public ResponseEntity<UpdateBbsResponse> updateBbs(@PathVariable Integer seq, @RequestBody UpdateBbsRequest req) {
		System.out.println("BbsController updateBbs " + new Date());

		return ResponseEntity.ok(service.updateBbs(seq, req));
	}

	/* [DELETE] /bbs/{seq} 게시글 삭제  */
	@ApiOperation(value=" deleteBbs() :   게시판의 게시글을 삭제하는 API ")
	@DeleteMapping("/{seq}")
	public ResponseEntity<DeleteBbsResponse> deleteBbs(@PathVariable Integer seq) {
		System.out.println("BbsController deleteBbs " + new Date());

		return ResponseEntity.ok(service.deleteBbs(seq));
	}
}








