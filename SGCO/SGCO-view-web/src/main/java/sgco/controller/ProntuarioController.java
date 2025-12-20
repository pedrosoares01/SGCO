package sgco.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import sgco.sgco.domain.Usuario;
import sgco.sgco.service.AgendaService;
import sgco.sgco.domain.Prontuario;
import sgco.sgco.service.ProntuarioService;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProntuarioController", urlPatterns ={"/ProntuarioController"})
public class ProntuarioController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null)
            action = "pesquisar";
        switch (action) {
            case "cadastrar":
                cadastrar(request,response);
                break;
            case "excluir":
                excluir(request,response);
                break;
            case "pesquisar":
                pesquisar(request,response);
                break;
            case "salvarEdicao":
                salvarEdicao(request,response);
                break;
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null)
            action = "carregar";
        switch (action) {
            case "carregar":
                carregarProfissionais(request);
                request.getRequestDispatcher("prontuario/prontuario.jsp").forward(request, response);
                break;
            case "editar":
                carregarParaEdicao(request,response);
                break;
            default:
                listarProfissionais(request, response);
                break;
        }
    }
    protected void listarProfissionais(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            carregarProfissionais(request);
            request.getRequestDispatcher("prontuario/prontuario.jsp").forward(request, response);
        } catch (Exception e){
            request.getRequestDispatcher("prontuario/prontuario.jsp").forward(request, response);
        }
    }
    protected void carregarProfissionais(HttpServletRequest request) {
        try {
            AgendaService service = new AgendaService();
            List<Usuario> lista = service.listarProfissionais();
            request.setAttribute("profissionais", lista);
        } catch (Exception e) {
            request.setAttribute("profissionais", null);
        }
    }
    protected void cadastrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String nome, ultimo_exame, profissional, endereco, telefone, naturalidade, profissao, nascimento, estado_civil, indicacao, convenio, qp, hda;
            char sexo;
            nome = request.getParameter("nome");
            ultimo_exame = request.getParameter("ultimoExame");
            profissional = request.getParameter("profissional");
            endereco = request.getParameter("endereco");
            telefone = request.getParameter("telefone");
            naturalidade = request.getParameter("naturalidade");
            profissao = request.getParameter("profissao");
            nascimento = request.getParameter("dataNascimento");
            estado_civil = request.getParameter("estadoCivil");
            indicacao = request.getParameter("indicacao");
            convenio = request.getParameter("convenio");
            qp = request.getParameter("qp");
            hda = request.getParameter("hda");
            sexo = request.getParameter("sexo").charAt(0);
            Prontuario p;
            p = new Prontuario();
            p.setNome(nome);
            p.setUltimo_exame(ultimo_exame);
            p.setProfissional(profissional);
            p.setEndereco(endereco);
            p.setTelefone(telefone);
            p.setNaturalidade(naturalidade);
            p.setProfissao(profissao);
            p.setNascimento(nascimento);
            p.setEstado_civil(estado_civil);
            p.setIndicacao(indicacao);
            p.setConvenio(convenio);
            p.setQp(qp);
            p.setHda(hda);
            p.setSexo(sexo);
            ProntuarioService prontuarioService = new ProntuarioService();
            prontuarioService.cadastrar(p);
            listarProfissionais(request, response);
            request.setAttribute("msg", "Cadastro realizado com sucesso!");
            request.setAttribute("tipoMensagem", "sucesso");
            request.getRequestDispatcher("prontuario/prontuario.jsp").forward(request, response);
        } catch (Exception e){
            carregarProfissionais(request);
            request.setAttribute("msg", e.getMessage());
            request.setAttribute("tipoMensagem", "erro");
            request.getRequestDispatcher("prontuario/prontuario.jsp").forward(request, response);
        }
    }
    protected void pesquisar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String nome = request.getParameter("nome");
            List<Prontuario> lista;
            Prontuario prontuario = new Prontuario();
            prontuario.setNome(nome);
            prontuario.setProfissional(nome);
            ProntuarioService prontuarioService = new ProntuarioService();
            lista = prontuarioService.pesquisarProntuario(prontuario);
            carregarProfissionais(request);
            request.setAttribute("nome", nome);
            request.setAttribute("resultados", lista);
            request.getRequestDispatcher("prontuario/prontuario.jsp").forward(request, response);
        } catch (Exception e){
            carregarProfissionais(request);
            request.setAttribute("msg", e.getMessage());
            request.setAttribute("tipoMensagem", "erro");
            request.getRequestDispatcher("prontuario/prontuario.jsp").forward(request, response);
        }
    }
    protected void excluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            ProntuarioService service = new ProntuarioService();
            service.excluir(id);
            request.setAttribute("msg", "Prontuário excluído com sucesso");
            request.setAttribute("tipoMensagem", "sucesso");
            carregarProfissionais(request);
            request.getRequestDispatcher("prontuario/prontuario.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("msg", e.getMessage());
            request.setAttribute("tipoMensagem", "erro");
            carregarProfissionais(request);
            request.getRequestDispatcher("prontuario/prontuario.jsp").forward(request, response);
        }
    }
    protected void carregarParaEdicao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            ProntuarioService service = new ProntuarioService();
            Prontuario prontuario = service.pesquisarProntuarioId(id);
            carregarProfissionais(request);
            request.setAttribute("prontuario", prontuario);
            request.getRequestDispatcher("prontuario/editar.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("msg", e.getMessage());
            request.setAttribute("tipoMensagem", "erro");
            request.getRequestDispatcher("prontuario/prontuario.jsp").forward(request, response);
        }
    }
    protected void salvarEdicao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Prontuario p = new Prontuario();
            p.setId(Integer.parseInt(request.getParameter("id")));
            p.setNome(request.getParameter("nome"));
            p.setUltimo_exame(request.getParameter("ultimoExame"));
            p.setProfissional(request.getParameter("profissional"));
            p.setEndereco(request.getParameter("endereco"));
            p.setTelefone(request.getParameter("telefone"));
            p.setNaturalidade(request.getParameter("naturalidade"));
            p.setProfissao(request.getParameter("profissao"));
            p.setNascimento(request.getParameter("dataNascimento"));
            p.setEstado_civil(request.getParameter("estadoCivil"));
            p.setIndicacao(request.getParameter("indicacao"));
            p.setConvenio(request.getParameter("convenio"));
            p.setSexo(request.getParameter("sexo").charAt(0));
            p.setQp(request.getParameter("qp"));
            p.setHda(request.getParameter("hda"));
            ProntuarioService service = new ProntuarioService();
            service.atualizar(p);
            request.setAttribute("msg", "Mudanças salvas com sucesso");
            request.setAttribute("tipoMensagem", "sucesso");
            response.sendRedirect("ProntuarioController");
        } catch (Exception e) {
            request.setAttribute("msg", e.getMessage());
            request.setAttribute("tipoMensagem", "erro");
            request.getRequestDispatcher("prontuario/prontuario.jsp").forward(request, response);
        }
    }

}
