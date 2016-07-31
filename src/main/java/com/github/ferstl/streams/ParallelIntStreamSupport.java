package com.github.ferstl.streams;

import java.util.IntSummaryStatistics;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.PrimitiveIterator.OfInt;
import java.util.concurrent.ForkJoinPool;
import java.util.function.BiConsumer;
import java.util.function.IntBinaryOperator;
import java.util.function.IntConsumer;
import java.util.function.IntFunction;
import java.util.function.IntPredicate;
import java.util.function.IntToDoubleFunction;
import java.util.function.IntToLongFunction;
import java.util.function.IntUnaryOperator;
import java.util.function.ObjIntConsumer;
import java.util.function.Supplier;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;


class ParallelIntStreamSupport extends AbstractParallelStreamSupport<Integer, IntStream> implements IntStream {

  ParallelIntStreamSupport(IntStream delegate, ForkJoinPool workerPool) {
    super(delegate, workerPool);
  }

  @Override
  public IntStream filter(IntPredicate predicate) {
    this.delegate = this.delegate.filter(predicate);
    return this;
  }

  @Override
  public IntStream map(IntUnaryOperator mapper) {
    this.delegate = this.delegate.map(mapper);
    return this;
  }

  @Override
  public <U> Stream<U> mapToObj(IntFunction<? extends U> mapper) {
    return new ParallelStreamSupport<U>(this.delegate.mapToObj(mapper), this.workerPool);
  }

  @Override
  public LongStream mapToLong(IntToLongFunction mapper) {
    return new ParallelLongStreamSupport(this.delegate.mapToLong(mapper), this.workerPool);
  }

  @Override
  public DoubleStream mapToDouble(IntToDoubleFunction mapper) {
    return new ParallelDoubleStreamSupport(this.delegate.mapToDouble(mapper), this.workerPool);
  }

  @Override
  public IntStream flatMap(IntFunction<? extends IntStream> mapper) {
    this.delegate = this.delegate.flatMap(mapper);
    return this;
  }

  @Override
  public IntStream distinct() {
    this.delegate = this.delegate.distinct();
    return this;
  }

  @Override
  public IntStream sorted() {
    this.delegate = this.delegate.sorted();
    return this;
  }

  @Override
  public IntStream peek(IntConsumer action) {
    this.delegate = this.delegate.peek(action);
    return this;
  }

  @Override
  public IntStream limit(long maxSize) {
    this.delegate = this.delegate.limit(maxSize);
    return this;
  }

  @Override
  public IntStream skip(long n) {
    this.delegate = this.delegate.skip(n);
    return this;
  }

  @Override
  public void forEach(IntConsumer action) {
    execute(() -> this.delegate.forEach(action));
  }

  @Override
  public void forEachOrdered(IntConsumer action) {
    execute(() -> this.delegate.forEachOrdered(action));
  }

  @Override
  public int[] toArray() {
    return execute(() -> this.delegate.toArray());
  }

  @Override
  public int reduce(int identity, IntBinaryOperator op) {
    return execute(() -> this.delegate.reduce(identity, op));
  }

  @Override
  public OptionalInt reduce(IntBinaryOperator op) {
    return execute(() -> this.delegate.reduce(op));
  }

  @Override
  public <R> R collect(Supplier<R> supplier, ObjIntConsumer<R> accumulator, BiConsumer<R, R> combiner) {
    return execute(() -> this.delegate.collect(supplier, accumulator, combiner));
  }

  @Override
  public int sum() {
    return execute(() -> this.delegate.sum());
  }

  @Override
  public OptionalInt min() {
    return execute(() -> this.delegate.min());
  }

  @Override
  public OptionalInt max() {
    return execute(() -> this.delegate.max());
  }

  @Override
  public long count() {
    return execute(() -> this.delegate.count());
  }

  @Override
  public OptionalDouble average() {
    return execute(() -> this.delegate.average());
  }

  @Override
  public IntSummaryStatistics summaryStatistics() {
    return execute(() -> this.delegate.summaryStatistics());
  }

  @Override
  public boolean anyMatch(IntPredicate predicate) {
    return execute(() -> this.delegate.anyMatch(predicate));
  }

  @Override
  public boolean allMatch(IntPredicate predicate) {
    return execute(() -> this.delegate.allMatch(predicate));
  }

  @Override
  public boolean noneMatch(IntPredicate predicate) {
    return execute(() -> this.delegate.noneMatch(predicate));
  }

  @Override
  public OptionalInt findFirst() {
    return execute(() -> this.delegate.findFirst());
  }

  @Override
  public OptionalInt findAny() {
    return execute(() -> this.delegate.findAny());
  }

  @Override
  public LongStream asLongStream() {
    return new ParallelLongStreamSupport(this.delegate.asLongStream(), this.workerPool);
  }

  @Override
  public DoubleStream asDoubleStream() {
    return new ParallelDoubleStreamSupport(this.delegate.asDoubleStream(), this.workerPool);
  }

  @Override
  public Stream<Integer> boxed() {
    return new ParallelStreamSupport<>(this.delegate.boxed(), this.workerPool);
  }

  @Override
  public OfInt iterator() {
    return this.delegate.iterator();
  }

  @Override
  public java.util.Spliterator.OfInt spliterator() {
    return this.delegate.spliterator();
  }
}
