package ru.nsu.sxrose1.graphs;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public interface DirectedGraph<T> {
  /**
   * @return set of this graph vertex references.
   */
  Set<DirectedGraphVertex<T>> vertices();

  /**
   * @return set of elements found in this graph.
   */
  default Set<T> elements() {
    return vertices().stream().map(DirectedGraphVertex::elem).collect(Collectors.toSet());
  }

  /**
   * @return set of this graph edges references.
   */
  Set<DirectedGraphEdge<T>> edges();

  /**
   * Add new vertex to graph.
   *
   * @param elem element inside new vertex.
   * @return new vertex reference.
   */
  DirectedGraphVertex<T> addVertex(T elem);

  /**
   * Delete vertex if it is from this graph, otherwise throws NoSuchElement.
   *
   * @param vertex vertex reference to delete.
   * @throws NoSuchElementException if vertex reference isn't belong to this graph.
   */
  default void deleteVertex(DirectedGraphVertex<T> vertex) throws NoSuchElementException {
    deleteVertexUnchecked(vertex);
  }

  /**
   * Delete vertex. Unsafely throws if vertex reference doesn't belong to this graph.
   *
   * @see DirectedGraph::deleteVertex
   * @param vertex vertex reference to delete.
   */
  void deleteVertexUnchecked(DirectedGraphVertex<T> vertex);

  /**
   * Delete vertex if it belongs to this graph, otherwise do nothing.
   *
   * @param vertex vertex reference to delete.
   */
  void deleteVertexMaybe(DirectedGraphVertex<T> vertex);

  /**
   * Delete vertices by element's equality.
   *
   * @param elem element.
   */
  void deleteVerticesByElem(T elem);

  /**
   * Create new edge from existing vertices.
   *
   * @param from vertex reference edge start.
   * @param to vertex reference edge end.
   * @return new edge reference if both vertexes belong to this graph, otherwise empty.
   */
  Optional<DirectedGraphEdge<T>> createEdge(DirectedGraphVertex<T> from, DirectedGraphVertex<T> to);

  /**
   * Functor mapping.
   *
   * @param f transform function
   * @return transformed graph.
   */
  <R> DirectedGraph<R> fmap(Function<? super T, ? extends R> f);
}
