package application;

import entities.Funcionario;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Principal {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final NumberFormat NUMBER_FORMAT = NumberFormat.getCurrencyInstance(
            Locale.of("pt", "BR")
    );

    static void main(String[] args) {
        List<Funcionario> listaFuncionarios = new ArrayList<>();

        inserirFuncionarios(listaFuncionarios);

        removerFuncionarioPeloNome(listaFuncionarios, "João");

        imprimirFuncionarios(listaFuncionarios);

        aumentoSalarialFuncionarios(listaFuncionarios, BigDecimal.valueOf(10.0));

        Map<String, List<Funcionario>> funcionariosAgrupadosPorFuncao = agruparFuncionariosPorFuncao(listaFuncionarios);

        imprimirFuncionariosPorFuncao(funcionariosAgrupadosPorFuncao);

        List<Funcionario> aniversariantesOutubro = filtrarAniversariantesPeloMes(listaFuncionarios, Month.OCTOBER);
        List<Funcionario> aniversariantesDezembro = filtrarAniversariantesPeloMes(listaFuncionarios, Month.DECEMBER);
        List<Funcionario> aniversariantesOutubroDezembro = Stream.concat(
                aniversariantesOutubro.stream(), aniversariantesDezembro.stream()).toList();

        System.out.println("Aniversariantes dos Meses de Outubro e Dezembro:");
        imprimirFuncionarios(aniversariantesOutubroDezembro);

        Funcionario funcionarioMaisVelho = funcionarioComMaiorIdade(listaFuncionarios);
        int idade = calcularIdade(funcionarioMaisVelho.getDataNascimento());

        System.out.println("Funcionário Mais Velho é o(a) " +  funcionarioMaisVelho.getNome() + " com " + idade + " anos");

        System.out.println();

        List<Funcionario> listaFuncionariosOrdenada = ordenarFuncionariosPorOrdemAlfabetica(listaFuncionarios);

        System.out.println("Funcionários ordenados por Ordem alfabética:");
        imprimirFuncionarios(listaFuncionariosOrdenada);

        BigDecimal total = totalSalarios(listaFuncionarios);
        System.out.println("Total dos salários dos funcionários: " + NUMBER_FORMAT.format(total));

        quantidadeSalariosMinimos(listaFuncionarios);
    }

    private static void inserirFuncionarios(List<Funcionario> listaFuncionarios) {
        String[][] tabelaFuncionarios = new String[][]{
            { "Maria",   "18/10/2000", "2009.44",  "Operador" },
            { "João",    "12/05/1990", "2284.38",  "Operador" },
            { "Caio",    "02/05/1961", "9836.14",  "Coordenador" },
            { "Miguel",  "14/10/1988", "19119.88", "Diretor" },
            { "Alice",   "05/01/1995", "2234.68",  "Recepcionista" },
            { "Heitor",  "19/11/1999", "1582.72",  "Operador" },
            { "Arthur",  "31/03/1993", "4071.84",  "Contador" },
            { "Laura",   "08/07/1994", "3017.45",  "Gerente" },
            { "Heloísa", "24/05/2003", "1606.85",  "Eletricista" },
            { "Helena",  "02/09/1996", "2799.93",  "Gerente" }
        };

        for (String[] funcionario : tabelaFuncionarios) {
            String nome = funcionario[0];
            LocalDate dataNascimento = LocalDate.parse(funcionario[1], DATE_TIME_FORMATTER);
            BigDecimal salario = new BigDecimal(funcionario[2]);
            String funcao = funcionario[3];

            listaFuncionarios.add(new Funcionario(nome,  dataNascimento, salario, funcao));
        }
    }

    private static void removerFuncionarioPeloNome(List<Funcionario> listaFuncionarios, String nome) {
        listaFuncionarios.removeIf(funcionario -> funcionario.getNome().equalsIgnoreCase(nome));
    }

    private static void imprimirFuncionarios(List<Funcionario> listaFuncionarios) {
        System.out.println();
        System.out.printf(
            "| %-20s | %-18s | %18s | %-20s |%n",
            "NOME",
            "DATA NASCIMENTO",
            "SALÁRIO",
            "FUNÇÃO"
        );

        System.out.println(
            "|----------------------|--------------------|--------------------|----------------------|"
        );

        for (Funcionario funcionario : listaFuncionarios) {
            System.out.printf(
                "| %-20s | %-18s | %18s | %-20s |%n",
                funcionario.getNome(),
                funcionario.getDataNascimento(),
                NUMBER_FORMAT.format(funcionario.getSalario()),
                funcionario.getFuncao()
            );
        }
        System.out.println();
    }

    private static void aumentoSalarialFuncionarios(List<Funcionario> listaFuncionarios, BigDecimal percentual) {
        BigDecimal fator = BigDecimal.ONE.add(percentual.movePointLeft(2));

        listaFuncionarios.forEach(funcionario -> funcionario.setSalario(funcionario.getSalario().multiply(fator)));
    }

    private static Map<String, List<Funcionario>> agruparFuncionariosPorFuncao(List<Funcionario> listaFuncionarios) {
        return listaFuncionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));
    }

    private static void imprimirFuncionariosPorFuncao(Map<String, List<Funcionario>> agrupamentoFuncionariosPorFuncao) {
        System.out.println();
        System.out.printf("| %-20s | %-20s |%n", "NOME", "FUNÇÃO");

        System.out.println(
                "|----------------------|----------------------|"
        );

        agrupamentoFuncionariosPorFuncao.forEach((key, funcionarios) -> {
            System.out.printf(
                "| %-20s | %-20s |%n",
                key,
                funcionarios.stream()
                        .map(Funcionario::getNome)
                        .collect(Collectors.joining(", "))
            );
        });
        System.out.println();
    }

    private static List<Funcionario> filtrarAniversariantesPeloMes(List<Funcionario> listaFuncionarios, Month mes) {
        return listaFuncionarios.stream()
                .filter(funcionario -> funcionario.getDataNascimento().getMonth() == mes)
                .toList();
    }

    private static Funcionario funcionarioComMaiorIdade(List<Funcionario> listaFuncionarios) {
        return listaFuncionarios.stream()
                .min(Comparator.comparing(Funcionario::getDataNascimento))
                .orElseThrow();
    }

    private static int calcularIdade(LocalDate dataNascimento) {
        return Period.between(dataNascimento, LocalDate.now()).getYears();
    }

    private static List<Funcionario> ordenarFuncionariosPorOrdemAlfabetica(List<Funcionario> listaFuncionarios) {
        return listaFuncionarios.stream()
                .sorted(Comparator.comparing(Funcionario::getNome))
                .toList();
    }

    private static BigDecimal totalSalarios(List<Funcionario> listaFuncionarios) {
        return listaFuncionarios
                .stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, (salario1, salario2) -> salario1.add(salario2));
    }

    private static void quantidadeSalariosMinimos(List<Funcionario> listaFuncionarios) {
        System.out.println();
        System.out.printf("| %-20s | %-40s |%n", "NOME", "QTD DE SALÁRIOS MÍNIMOS");

        System.out.println("|----------------------|------------------------------------------|");

        for (Funcionario funcionario : listaFuncionarios) {
            System.out.printf(
                "| %-20s | %-40s |%n",
                funcionario.getNome(),
                funcionario.getSalario().divide(BigDecimal.valueOf(1212.00), 2, RoundingMode.HALF_UP)
            );
        }
    }
}
