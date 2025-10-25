package ru.nsu.sxrose1.maps;

import java.util.Optional;

public class HashMap<K, E> implements Map<K, E> {
  private enum SlotTag {
    OCCUPIED,
    EMPTY,
    DELETED
  }

  private record Slot(SlotTag tag, Object key, Object element) {}

  HashMap() {}

  /** {@inheritDoc} */
  @Override
  public Map<K, E> insert(K key, E element) {
    return null;
  }

  /** {@inheritDoc} */
  @Override
  public Optional<E> find(K key) {
    return Optional.empty();
  }

  /** {@inheritDoc} */
  @Override
  public Optional<Map<K, E>> delete(K key) {
    return Optional.empty();
  }
}
