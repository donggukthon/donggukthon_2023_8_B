package Donggukthon.santa.web.controller;

import Donggukthon.santa.service.MemberService;
import Donggukthon.santa.service.TreeService;
import Donggukthon.santa.web.apiResponse.ApiResponse;
import Donggukthon.santa.web.apiResponse.ErrorStatus;
import Donggukthon.santa.web.apiResponse.SuccessStatus;
import Donggukthon.santa.web.dto.response.MemberResponseDTO;
import Donggukthon.santa.web.dto.response.TreeResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/tree")
@RestController
public class TreeController {

    @Autowired
    private TreeService treeService;
    @Autowired
    private MemberService memberService;

    // (모든 유저와 그 유저의 데코레이션) 리스트 조회
    @GetMapping
    public ApiResponse<List<TreeResponseDTO>> getTreeDecoration(){

        List<TreeResponseDTO> allTreeResponseDTOs = treeService.getAllTreeDecorations();
        return new ApiResponse<>(SuccessStatus.READ_TREE_DECORATION, allTreeResponseDTOs);
    }

    // (하나의 유저와 그 유저의 데코레이션) 리스트 조회
    @GetMapping("/details/{id}")
    public ApiResponse<MemberResponseDTO.MetaResponseDTO> getUserTreeDecoration(@PathVariable("id") Long id){

        try{
            MemberResponseDTO.MetaResponseDTO metaResponseDTO = memberService.findById(id)
                    .orElseThrow(RuntimeException::new);
            return new ApiResponse<>(SuccessStatus.READ_MEMBER_METADATA, metaResponseDTO);

        } catch (RuntimeException e) {
            return new ApiResponse<>(ErrorStatus.USER_INFO);
        }

    }


}
