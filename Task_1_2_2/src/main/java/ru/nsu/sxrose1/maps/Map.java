package ru.nsu.sxrose1.maps;

import java.util.NoSuchElementException;
import java.util.Optional;

public interface Map<K, E> {
  /**
   * Inserts new element with key.
   *
   * @param key key.
   * @param element element.
   * @return this.
   */
  Map<K, E> insert(K key, E element);

  /**
   * Finds element under the key.
   *
   * @param key key.
   * @return element if it exists, empty otherwise.
   */
  Optional<E> find(K key);

  /**
   * Delete element by key.
   *
   * @param key key.
   * @return this if key exists, empty otherwise.
   */
  Optional<Map<K, E>> delete(K key);

  /**
   * Delete element by key. Throws if key doesn't exist.
   *
   * @param key key.
   * @return this.
   * @throws NoSuchElementException if key is missing from map.
   */
  default Map<K, E> deleteExcept(K key) throws NoSuchElementException {
    return delete(key).orElseThrow(NoSuchElementException::new);
  }
}
