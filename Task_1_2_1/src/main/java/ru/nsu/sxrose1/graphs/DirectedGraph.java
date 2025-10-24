package ru.nsu.sxrose1.graphs;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

public interface DirectedGraph<T> {
  /**
   * @return unmodifiable set of this graph vertex references.
   */
  Set<T> vertices();

  /**
   * @return unmodifiable set of this graph edges references.
   */
  Set<DirectedGraphEdge<T>> edges();

  /**
   * Add new vertex to graph.
   *
   * @param elem element inside new vertex.
   * @return this if vertex was added, empty if vertex with that element already exist.
   */
  Optional<DirectedGraph<T>> addVertex(T elem);

  /**
   * Add new vertex to graph or do nothing if it exists.
   *
   * @param elem element inside new vertex.
   * @return this.
   */
  default DirectedGraph<T> addVertexMaybe(T elem) {
    return addVertex(elem).orElse(this);
  }

  /**
   * Delete vertex if it exists in this graph.
   *
   * @param elem element vertex to delete.
   * @return this if element exists, empty otherwise.
   */
  Optional<DirectedGraph<T>> deleteVertex(T elem);

  /**
   * Delete vertex if it exists in this graph, do nothing otherwise.
   *
   * @param elem element vertex to delete.
   * @return this.
   */
  default DirectedGraph<T> deleteVertexMaybe(T elem) {
    return deleteVertex(elem).orElse(this);
  }

  /**
   * Delete vertex if it exists in this graph, otherwise throws NoSuchElement.
   *
   * @param elem element vertex to delete.
   * @return this
   * @throws NoSuchElementException if vertex reference isn't belong to this graph.
   */
  default DirectedGraph<T> deleteVertexUnsafe(T elem) throws NoSuchElementException {
    return deleteVertex(elem).orElseThrow(NoSuchElementException::new);
  }

  /**
   * Create new edge from existing vertices.
   *
   * @param from vertex element edge start.
   * @param to vertex element edge end.
   * @return new edge reference if both vertexes belong to this graph, otherwise empty.
   */
  Optional<DirectedGraphEdge<T>> createEdge(T from, T to);

  /**
   * Functor mapping.
   *
   * @param f transform function
   * @return transformed graph.
   */
  <R> DirectedGraph<R> map(Function<? super T, ? extends R> f);

  /**
   * Vertex neighbourhood.
   *
   * @param elem vertex element to search.
   * @return set containing neighbourhood of given vertex element if vertex belongs to graph, empty
   *     otherwise.
   */
  Optional<Set<T>> neighbourhood(T elem);

  /**
   * @return topological sorting of this graph if it exists, empty otherwise.
   */
  Optional<ArrayList<T>> topsort();
}
