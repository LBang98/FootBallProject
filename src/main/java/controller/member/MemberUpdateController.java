package controller.member;

import data.dto.MemberDto;
import data.service.MemberService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import naver.cloud.NcpObjectStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberUpdateController {

    @NonNull
    private MemberService memberService;

    private String bucketName="bitcamp-bh-98";
    private String folderName="photocommon";

    @Autowired
    private NcpObjectStorageService storageService;

    @ResponseBody
    @PostMapping("/upload")
    public Map<String,String> uploadPhoto(
            @RequestParam("upload") MultipartFile upload,
            @RequestParam int num,
            HttpServletRequest request
    )
    {
        //		String savePath=request.getSession().getServletContext().getRealPath("/save");
        //		//업로드한 파일의 확장자 분리
        //		String ext=upload.getOriginalFilename().split("\\.")[1];
        //		//업로드할 파일명
        //		String photo=UUID.randomUUID()+"."+ext;
        //
        //		//실제 업로드
        //		try {
        //			upload.transferTo(new File(savePath+"/"+photo));
        //		} catch (IllegalStateException | IOException e) {
        //			// TODO Auto-generated catch block
        //			e.printStackTrace();
        //		}

        //스토리지에 업로드하기
        String photo=storageService.uploadFile(bucketName, folderName, upload);

        //db 에서 photo 수정
        memberService.updatePhoto(num, photo);

        Map<String,String> map=new HashMap<>();
        map.put("photoname", photo);
        return map;
    }

    //수정폼-이름,핸드폰,이메일,주소,생년월일 만 폼에 나타나도록
    @GetMapping("/updateform")
    public String updateForm(@RequestParam String id,Model model)
    {
        //db로부터 dto 얻기
        int num = memberService.getNum(id);
        MemberDto dto=memberService.getData(num);
        model.addAttribute("dto", dto);
        return "login/mypage";
    }


    //수정후 디테일 페이지로 이동
    @PostMapping("/update")
    public String update(@ModelAttribute MemberDto dto)
    {

        //수정
        memberService.updateMember(dto);
        return "redirect:./updateform?num="+dto.getNum();
    }

    //{"status":"success" or "fail"}
/*    @ResponseBody
    @GetMapping("/delete")
    public Map<String, String> delete(@RequestParam int num,@RequestParam String passwd)
    {
        Map<String, String> map=new HashMap<String, String>();
        //비번이 맞을경우 데이타 삭제
        boolean b=memberService.isEqualPassCheck(num, passwd);
        if(b) {
            memberService.deleteMember(num);
        }
        map.put("status", b?"success":"fail");
        return map;
    }*/
}