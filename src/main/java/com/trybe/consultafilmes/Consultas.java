package com.trybe.consultafilmes;

import static java.util.Collections.emptyList;
import static java.util.Collections.emptyMap;
import static java.util.Collections.emptySet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Consultas {

  private final Collection<Filme> filmes;

  public Consultas(Collection<Filme> filmes) {
    this.filmes = filmes;
  }

  /**
   * Consulta 1: a partir da coleção de filmes desta classe, este método retorna o conjunto
   * de atores que interpretaram a si próprios em pelo menos um desses filmes.
   *
   * <p>Considera-se "atores que interpretaram a si próprios" aqueles que têm o seu nome como
   * uma das chaves do Map `atoresPorPersonagem` e também como um dos itens pertencentes ao
   * conjunto associado a esta mesma chave.</p>
   */
  public Set<String> atoresQueInterpretaramSiProprios() {
    Set<String> result = new HashSet<>();
    filmes.stream().forEach(movie -> {
      Map<String, Set<String>> actorsByCharacter = movie.atoresPorPersonagem;
      actorsByCharacter.forEach((character, actors) -> {
        if (!result.contains(character) && actors.contains(character)) {
          result.add(character);
        }
      });
    });
    return result;
  }

  /**
   * Consulta 2: a partir da coleção de filmes desta classe, este método retorna a lista de atores
   * que atuaram em pelo menos um filme de um determinado diretor. A lista retornada está disposta
   * em ordem alfabética.
   *
   * <p>Considera-se que um ator tenha atuado em um filme de um determinado diretor se ele tem o
   * seu nome como um dos itens do campo `atores`, ao mesmo tempo em que o diretor em questão
   * tem o seu nome como um dos itens do campo `diretores` do mesmo filme.</p>
   */
  public List<String> atoresQueAtuaramEmFilmesDoDiretorEmOrdemAlfabetica(String diretor) {
    List<String> result = new ArrayList<>();
    filmes.stream().forEach(movie -> {
      Set<String> actors = movie.atores;
      actors.forEach(actor -> {
        if (movie.diretores.contains(diretor) && !result.contains(actor)) {
          result.add(actor);
        }
      });
    });
    Collections.sort(result);
    return result;
  }

  /**
   * Consulta 3: a partir da coleção de filmes desta classe, este método retorna a lista de filmes
   * em que pelo menos um dos diretores tenha atuado. A lista retornada está disposta em ordem de
   * lançamento, com os filmes mais recentes no início.
   *
   * <p>Considera-se "filmes em que pelo menos um dos diretores tenha atuado" aqueles em que
   * pelo menos um dos itens do campo `diretores` também é um item do campo `atores`.</p>
   */
  public List<Filme> filmesEmQuePeloMenosUmDiretorAtuouMaisRecentesPrimeiro() {
    List<Filme> result = new ArrayList<>();
    filmes.stream().forEach(movie -> {
      Set<String> directors = movie.diretores;
      directors.forEach(director -> {
        if (!result.contains(movie) && movie.atores.contains(director)) {
          result.add(movie);
        }
      });
    });
    Collections.sort(result, (movie1, movie2) -> movie2.anoDeLancamento - movie1.anoDeLancamento);
    return result;
  }

  /**
   * Consulta 4: a partir da coleção de filmes desta classe, este método retorna um Map contendo
   * todos os filmes lançados em um determinado ano agrupados por categoria.
   *
   * <p>Cada chave do Map representa uma categoria, enquanto cada valor representa o
   * conjunto de filmes que se encaixam na categoria da chave correspondente.</p>
   */
  public Map<String, Set<Filme>> filmesLancadosNoAnoAgrupadosPorCategoria(int ano) {
    return emptyMap(); // TODO: Implementar (bônus).
  }
}