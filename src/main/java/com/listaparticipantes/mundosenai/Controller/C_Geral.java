package com.listaparticipantes.mundosenai.Controller;

import com.listaparticipantes.mundosenai.Model.M_Resposta;
import com.listaparticipantes.mundosenai.Service.S_Geral;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class C_Geral {
    @GetMapping("/")
    public String getHome(){
        return "index";
    }

    @PostMapping("/")
    @ResponseBody
    public M_Resposta postHome(@RequestParam("nome") String nome,
                               @RequestParam("data_nasc") String data_nasc,
                               @RequestParam("email") String email,
                               @RequestParam("telefone") String telefone){
        return S_Geral.salvarVisitante(nome,data_nasc,email,telefone);
    }


}
