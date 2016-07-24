package com.github.ferstl;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Optional;
import java.util.Spliterator;
import java.util.concurrent.ForkJoinPool;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;
import java.util.stream.Collector;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class ParallelStreamSupport<T> implements Stream<T> {

  private Stream<T> delegate;
  private final ForkJoinPool workerPool;

  private ParallelStreamSupport(Stream<T> delegate, ForkJoinPool pool) {
    this.delegate = delegate;
    this.workerPool = pool;
  }

  public static <T> Stream<T> parallelStream(Collection<T> collection, ForkJoinPool workerPool) {
    return new ParallelStreamSupport<T>(collection.parallelStream(), workerPool);
  }

  // BaseStream methods

  @Override
  public Iterator<T> iterator() {
    return this.delegate.iterator();
  }

  @Override
  public Spliterator<T> spliterator() {
    return this.delegate.spliterator();
  }

  @Override
  public boolean isParallel() {
    return this.delegate.isParallel();
  }

  @Override
  public Stream<T> sequential() {
    this.delegate = this.delegate.sequential();
    return this;
  }

  @Override
  public Stream<T> parallel() {
    this.delegate = this.delegate.parallel();
    return this;
  }

  @Override
  public Stream<T> unordered() {
    this.delegate = this.delegate.unordered();
    return this;
  }

  @Override
  public Stream<T> onClose(Runnable closeHandler) {
    this.delegate = this.delegate.onClose(closeHandler);
    return this;
  }

  @Override
  public void close() {
    this.delegate.close();
  }

  // Stream methods

  @Override
  public Stream<T> filter(Predicate<? super T> predicate) {
    throw new UnsupportedOperationException("Not yet implemented");
  }

  @Override
  public <R> Stream<R> map(Function<? super T, ? extends R> mapper) {
    throw new UnsupportedOperationException("Not yet implemented");
  }

  @Override
  public IntStream mapToInt(ToIntFunction<? super T> mapper) {
    throw new UnsupportedOperationException("Not yet implemented");
  }

  @Override
  public LongStream mapToLong(ToLongFunction<? super T> mapper) {
    throw new UnsupportedOperationException("Not yet implemented");
  }

  @Override
  public DoubleStream mapToDouble(ToDoubleFunction<? super T> mapper) {
    throw new UnsupportedOperationException("Not yet implemented");
  }

  @Override
  public <R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper) {
    throw new UnsupportedOperationException("Not yet implemented");
  }

  @Override
  public IntStream flatMapToInt(Function<? super T, ? extends IntStream> mapper) {
    throw new UnsupportedOperationException("Not yet implemented");
  }

  @Override
  public LongStream flatMapToLong(Function<? super T, ? extends LongStream> mapper) {
    throw new UnsupportedOperationException("Not yet implemented");
  }

  @Override
  public DoubleStream flatMapToDouble(Function<? super T, ? extends DoubleStream> mapper) {
    throw new UnsupportedOperationException("Not yet implemented");
  }

  @Override
  public Stream<T> distinct() {
    throw new UnsupportedOperationException("Not yet implemented");
  }

  @Override
  public Stream<T> sorted() {
    throw new UnsupportedOperationException("Not yet implemented");
  }

  @Override
  public Stream<T> sorted(Comparator<? super T> comparator) {
    throw new UnsupportedOperationException("Not yet implemented");
  }

  @Override
  public Stream<T> peek(Consumer<? super T> action) {
    throw new UnsupportedOperationException("Not yet implemented");
  }

  @Override
  public Stream<T> limit(long maxSize) {
    throw new UnsupportedOperationException("Not yet implemented");
  }

  @Override
  public Stream<T> skip(long n) {
    throw new UnsupportedOperationException("Not yet implemented");
  }

  @Override
  public void forEach(Consumer<? super T> action) {
    throw new UnsupportedOperationException("Not yet implemented");
  }

  @Override
  public void forEachOrdered(Consumer<? super T> action) {
    throw new UnsupportedOperationException("Not yet implemented");
  }

  @Override
  public Object[] toArray() {
    throw new UnsupportedOperationException("Not yet implemented");
  }

  @Override
  public <A> A[] toArray(IntFunction<A[]> generator) {
    throw new UnsupportedOperationException("Not yet implemented");
  }

  @Override
  public T reduce(T identity, BinaryOperator<T> accumulator) {
    throw new UnsupportedOperationException("Not yet implemented");
  }

  @Override
  public Optional<T> reduce(BinaryOperator<T> accumulator) {
    throw new UnsupportedOperationException("Not yet implemented");
  }

  @Override
  public <U> U reduce(U identity, BiFunction<U, ? super T, U> accumulator, BinaryOperator<U> combiner) {
    throw new UnsupportedOperationException("Not yet implemented");
  }

  @Override
  public <R> R collect(Supplier<R> supplier, BiConsumer<R, ? super T> accumulator, BiConsumer<R, R> combiner) {
    throw new UnsupportedOperationException("Not yet implemented");
  }

  @Override
  public <R, A> R collect(Collector<? super T, A, R> collector) {
    throw new UnsupportedOperationException("Not yet implemented");
  }

  @Override
  public Optional<T> min(Comparator<? super T> comparator) {
    throw new UnsupportedOperationException("Not yet implemented");
  }

  @Override
  public Optional<T> max(Comparator<? super T> comparator) {
    throw new UnsupportedOperationException("Not yet implemented");
  }

  @Override
  public long count() {
    throw new UnsupportedOperationException("Not yet implemented");
  }

  @Override
  public boolean anyMatch(Predicate<? super T> predicate) {
    throw new UnsupportedOperationException("Not yet implemented");
  }

  @Override
  public boolean allMatch(Predicate<? super T> predicate) {
    throw new UnsupportedOperationException("Not yet implemented");
  }

  @Override
  public boolean noneMatch(Predicate<? super T> predicate) {
    throw new UnsupportedOperationException("Not yet implemented");
  }

  @Override
  public Optional<T> findFirst() {
    throw new UnsupportedOperationException("Not yet implemented");
  }

  @Override
  public Optional<T> findAny() {
    throw new UnsupportedOperationException("Not yet implemented");
  }

}

