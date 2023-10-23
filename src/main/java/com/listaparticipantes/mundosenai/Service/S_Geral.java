package com.listaparticipantes.mundosenai.Service;

import com.listaparticipantes.mundosenai.Model.M_Resposta;
import com.listaparticipantes.mundosenai.Model.M_Visitante;
import com.listaparticipantes.mundosenai.Repository.R_Visitante;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class S_Geral {
    private static R_Visitante r_visitante;

    public S_Geral(R_Visitante r_visitante) {
        this.r_visitante = r_visitante;
    }

    public static M_Resposta salvarVisitante(String nome, String data_nasc, String email, String telefone){
        boolean podeSalvar = true;
        String mensagem = "";
        telefone = limparNumero(telefone);

        if(textoEstaVazio(nome)){
            podeSalvar = false;
            mensagem += "O nome precisa ser preenchido";
        }
        if(!validarEmail(email) && !validarNumeroTelefone(telefone)){
            podeSalvar = false;
            mensagem += "O e-mail ou telefone precisa ser preenchido";
        }

        if(podeSalvar){
            M_Visitante m_visitante = new M_Visitante();
            m_visitante.setNome(nome);
            m_visitante.setTelefone(telefone.equals("") ? null : Long.parseLong(telefone));
            m_visitante.setEmail(email.equals("") ? null : email);
            try{
                m_visitante.setData(LocalDate.parse(data_nasc));
            }catch (Exception e){
                m_visitante.setData(null);
            }
            m_visitante.setCreatedAt(LocalDateTime.now());
            try {
                r_visitante.save(m_visitante);
                mensagem += "Cadastro efetuado com sucesso";
            }catch (DataIntegrityViolationException e){
                podeSalvar = false;
                mensagem += "Falha ao salvar na base de dados";
            }
        }
        M_Visitante m_visitante = new M_Visitante();
        return new M_Resposta(podeSalvar, mensagem);
    }

    public static boolean validarEmail(String email) {
        // Defina a expressão regular para validar o e-mail
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        // Crie o objeto de padrão
        Pattern pattern = Pattern.compile(regex);
        // Crie o objeto de correspondência
        Matcher matcher = pattern.matcher(email);
        // Realize a correspondência e retorne true se o e-mail for válido
        return matcher.matches();
    }

    public static boolean textoEstaVazio(String texto){
        return texto == null || texto.trim().equals("");
    }

    public static boolean validarNumeroTelefone(String numeroTelefone) {
        String regex = "\\d{10,11}";
        return numeroTelefone.matches(regex);
    }

    public static String limparNumero(String numero) {
        // Remove caracteres, apenas os dígitos serão mantidos
        return numero.replaceAll("[^0-9]", "");
    }



}
