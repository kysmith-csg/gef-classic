/*******************************************************************************
 * Copyright (c) 2011, 2023 Google, Inc.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Google, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.draw2d.test;

import org.eclipse.draw2d.geometry.Interval;

import org.junit.Test;

/**
 * Tests for {@link Interval} class.
 *
 * @author mitin_aa
 * @author lobas_av
 */
public class IntervalTest extends BaseTestCase {

	@Test
	public void testConstructor() throws Exception {
		assertEquals(0, 0, new Interval());
	}

	@Test
	public void testConstructorIntInt() throws Exception {
		assertEquals(-1, 2, new Interval(-1, 2));
	}

	@Test
	public void testConstructorInterval() throws Exception {
		Interval template = new Interval(-1, 2);
		assertEquals(-1, 2, new Interval(template));
		assertEquals(-1, 2, template); // assert read only argument interval
	}

	@SuppressWarnings("static-method")
	@Test
	public void testGetCopy() throws Exception {
		Interval template = new Interval(-1, 2);
		Interval testInterval = template.getCopy();
		assertNotNull(testInterval);
		assertNotSame(template, testInterval);
		assertEquals(template, testInterval);
	}

	@Test
	public void testEnd() throws Exception {
		Interval interval = new Interval(1, 2);
		assertEquals(3, interval.end());
		assertEquals(1, 2, interval);
	}

	@SuppressWarnings("static-method")
	@Test
	public void testIsEmpty() throws Exception {
		Interval interval = new Interval();
		assertTrue(interval.isEmpty());

		interval = new Interval(1, 2);
		assertFalse(interval.isEmpty());
	}

	@SuppressWarnings("static-method")
	@Test
	public void testCenter() throws Exception {
		Interval interval = new Interval(1, 2);
		assertEquals(2, interval.center());

		interval = new Interval(1, 3);
		assertEquals(2, interval.center());

		interval = new Interval(1, 4);
		assertEquals(3, interval.center());

		interval = new Interval();
		assertEquals(0, interval.center());

	}

	@SuppressWarnings("static-method")
	@Test
	public void testDistance() throws Exception {
		Interval interval = new Interval(10, 100);
		assertEquals(7, interval.distance(3));
		assertEquals(0, interval.distance(10));
		assertEquals(0, interval.distance(50));
		assertEquals(0, interval.distance(110));
		assertEquals(40, interval.distance(150));
	}

	@SuppressWarnings("static-method")
	@Test
	public void testContains() throws Exception {
		Interval interval = new Interval(1, 2);
		assertFalse(interval.contains(0));
		assertTrue(interval.contains(1));
		assertTrue(interval.contains(2));
		assertFalse(interval.contains(3));
		assertFalse(interval.contains(4));
	}

	@SuppressWarnings("static-method")
	@Test
	public void testIntersects() throws Exception {
		// not intersects
		Interval interval1 = new Interval(10, 20);
		Interval interval2 = new Interval(2, 6);
		assertFalse(interval1.intersects(interval2));
		assertFalse(interval2.intersects(interval1));

		// fully overlaps
		interval1 = new Interval(10, 20);
		interval2 = new Interval(15, 3);
		assertTrue(interval1.intersects(interval2));
		assertTrue(interval2.intersects(interval1));

		// partly intersects
		interval1 = new Interval(10, 20);
		interval2 = new Interval(15, 20);
		assertTrue(interval1.intersects(interval2));
		assertTrue(interval2.intersects(interval1));
	}

	@SuppressWarnings("static-method")
	@Test
	public void testIsLeadingOf() throws Exception {
		Interval interval1 = new Interval(10, 20);
		Interval interval2 = new Interval(15, 20);
		assertTrue(interval1.isLeadingOf(interval2));
		assertFalse(interval2.isLeadingOf(interval1));
	}

	@SuppressWarnings("static-method")
	@Test
	public void testIsTrailingOf() throws Exception {
		Interval interval1 = new Interval(10, 20);
		Interval interval2 = new Interval(15, 20);
		assertFalse(interval1.isTrailingOf(interval2));
		assertTrue(interval2.isTrailingOf(interval1));
	}

	@SuppressWarnings("static-method")
	@Test
	public void testGetIntersection() throws Exception {
		// intervals intersect
		Interval interval1 = new Interval(10, 20);
		Interval interval2 = new Interval(15, 20);
		Interval intersection = interval2.getIntersection(interval1);
		assertTrue(intersection.equals(new Interval(15, 15)));

		interval1 = new Interval(10, 20);
		interval2 = new Interval(15, 20);
		intersection = interval1.getIntersection(interval2);
		assertTrue(intersection.equals(new Interval(15, 15)));

		// no intersection
		interval1 = new Interval(10, 5);
		interval2 = new Interval(20, 10);
		intersection = interval1.getIntersection(interval2);
		assertTrue(intersection.length() == 0);
	}

	@Test
	public void testGrowLeading() throws Exception {
		// to right
		Interval interval = new Interval(1, 2);
		assertEquals(3, 0, interval.growLeading(2));
		// to left
		interval = new Interval(1, 2);
		assertEquals(0, 3, interval.growLeading(-1));
	}

	@Test
	public void testGrowTrailing() throws Exception {
		// to right
		Interval interval = new Interval(1, 2);
		assertEquals(1, 4, interval.growTrailing(2));

		// to left
		interval = new Interval(1, 2);
		assertEquals(1, 1, interval.growTrailing(-1));
	}

	@SuppressWarnings("static-method")
	@Test
	public void testGetRightMostIntervalIndex() throws Exception {
		Interval[] intervals = { new Interval(1, 5), new Interval(8, 1) };
		assertEquals(-1, Interval.getRightMostIntervalIndex(intervals, 3));
		assertEquals(0, Interval.getRightMostIntervalIndex(intervals, 7));
		assertEquals(1, Interval.getRightMostIntervalIndex(intervals, 9));
	}
}