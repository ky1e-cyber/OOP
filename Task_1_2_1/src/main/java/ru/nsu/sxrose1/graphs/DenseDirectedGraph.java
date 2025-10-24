package ru.nsu.sxrose1.graphs;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

public final class DenseDirectedGraph<T> implements DirectedGraph<T> {



  /** {@inheritDoc} */
  @Override
  public Set<T> vertices() {
    return Set.of();
  }

  @Override
  public Set<DirectedGraphEdge<T>> edges() {
    return Set.of();
  }

  @Override
  public Optional<DirectedGraph<T>> addVertex(T elem) {
    return Optional.empty();
  }

  @Override
  public Optional<DirectedGraph<T>> deleteVertex(T elem) {
    return Optional.empty();
  }

  @Override
  public Optional<DirectedGraphEdge<T>> createEdge(T from, T to) {
    return Optional.empty();
  }

  @Override
  public <R> DirectedGraph<R> map(Function<? super T, ? extends R> f) {
    return null;
  }

  @Override
  public Optional<Set<T>> neighbourhood(T elem) {
    return Optional.empty();
  }

  @Override
  public Optional<ArrayList<T>> topsort() {
    return Optional.empty();
  }
}
