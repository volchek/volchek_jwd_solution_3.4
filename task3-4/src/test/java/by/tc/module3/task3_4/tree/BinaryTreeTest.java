package by.tc.module3.task3_4.tree;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

public class BinaryTreeTest {

	@Test
	public void shouldCreateEmptyTree() {
		BinaryTree<Integer> tree = new BinaryTree<Integer>();
		assertTrue(tree.getSize() == 0);
	}

	@Test
	public void shouldCreateNonemptyTree() {
		Integer x = 10;
		BinaryTree<Integer> tree = new BinaryTree<Integer>(x);
		assertTrue(tree.find(x));
	}

	@Test
	public void shouldAddNode() {
		BinaryTree<Integer> tree = new BinaryTree<Integer>();

		tree.add(5);
		assertTrue(tree.getSize() == 1);

		tree.add(6);
		assertTrue(tree.getSize() == 2);

		tree.add(4);
		assertTrue(tree.getSize() == 3);

		tree.add(4);
		assertTrue(tree.getSize() == 4);
	}

	@Test
	public void shouldGetMinMaxInEmptyTree() {
		BinaryTree<Integer> tree = new BinaryTree<Integer>();
		assertTrue(tree.min() == null);
		assertTrue(tree.max() == null);
	}

	@Test
	public void shouldGetMin() {
		BinaryTree<Integer> tree = createTree();
		assertTrue(tree.min() == 1);
	}

	@Test
	public void shouldGetMax() {
		BinaryTree<Integer> tree = createTree();
		assertTrue(tree.max() == 8);
	}

	@Test
	public void shouldFindValue() {
		BinaryTree<Integer> tree = createTree();

		assertTrue(!tree.find(0));
		assertTrue(tree.find(1));
		assertTrue(tree.find(2));
		assertTrue(!tree.find(3));
		assertTrue(tree.find(4));
		assertTrue(tree.find(5));
		assertTrue(tree.find(6));
		assertTrue(tree.find(7));
		assertTrue(tree.find(8));
		assertTrue(!tree.find(9));
		assertTrue(!tree.find(10));
	}

	@Test
	public void shouldPreOrderTraverse() {
		BinaryTree<Integer> tree = createTree();
		List<Integer> nodes = tree.preOrderTraverse();

		assertTrue(nodes.equals(Arrays.asList(5, 4, 1, 2, 4, 6, 7, 8)));
	}

	@Test
	public void shouldInOrderTraverse() {
		BinaryTree<Integer> tree = createTree();
		List<Integer> nodes = tree.inOrderTraverse();

		assertTrue(nodes.equals(Arrays.asList(1, 2, 4, 4, 5, 6, 7, 8)));
	}

	@Test
	public void shouldPostOrderTraverse() {
		BinaryTree<Integer> tree = createTree();
		List<Integer> nodes = tree.postOrderTraverse();

		assertTrue(nodes.equals(Arrays.asList(2, 1, 4, 4, 8, 7, 6, 5)));
	}

	@Test
	public void shouldRemoveFromEmptyTree() {
		BinaryTree<Integer> tree = new BinaryTree<Integer>();
		assertTrue(!tree.remove(10));
	}

	@Test
	public void shouldRemoveMissingElement() {
		BinaryTree<Integer> tree = createTree();

		assertTrue(!tree.remove(10));
		assertTrue(!tree.remove(0));
		assertTrue(!tree.remove(3));
	}

	@Test
	public void shouldRemoveRightLeaf() {
		BinaryTree<Integer> tree = createTree();

		assertTrue(tree.remove(2));
		assertTrue(tree.getSize() == 7);
		List<Integer> nodes = tree.preOrderTraverse();

		assertTrue(nodes.equals(Arrays.asList(5, 4, 1, 4, 6, 7, 8)));

		assertTrue(tree.remove(8));
		assertTrue(tree.getSize() == 6);
		nodes = tree.preOrderTraverse();

		assertTrue(nodes.equals(Arrays.asList(5, 4, 1, 4, 6, 7)));
	}

	@Test
	public void shouldRemoveAllRightLeaf() {
		BinaryTree<Integer> tree = createTree();

		assertTrue(tree.remove(8));
		assertTrue(tree.getSize() == 7);
		List<Integer> nodes = tree.preOrderTraverse();
		assertTrue(nodes.equals(Arrays.asList(5, 4, 1, 2, 4, 6, 7)));

		assertTrue(tree.remove(7));
		assertTrue(tree.getSize() == 6);
		nodes = tree.preOrderTraverse();
		assertTrue(nodes.equals(Arrays.asList(5, 4, 1, 2, 4, 6)));

		assertTrue(tree.remove(6));
		assertTrue(tree.getSize() == 5);
		nodes = tree.preOrderTraverse();
		assertTrue(nodes.equals(Arrays.asList(5, 4, 1, 2, 4)));

	}

	@Test
	public void shouldRemoveRightAndLeftLeaf() {
		BinaryTree<Integer> tree = createTree();

		assertTrue(tree.remove(2));
		List<Integer> nodes = tree.preOrderTraverse();
		assertTrue(nodes.equals(Arrays.asList(5, 4, 1, 4, 6, 7, 8)));

		assertTrue(tree.remove(1));
		assertTrue(tree.getSize() == 6);
		nodes = tree.preOrderTraverse();
		assertTrue(nodes.equals(Arrays.asList(5, 4, 4, 6, 7, 8)));
	}

	@Test
	public void shouldRemoveOneInternalNode() {
		BinaryTree<Integer> tree = new BinaryTree<Integer>(8);

		tree.add(3);
		tree.add(4);
		tree.add(1);
		tree.add(5);

		assertTrue(tree.remove(3));
		List<Integer> nodes = tree.preOrderTraverse();
		assertTrue(nodes.equals(Arrays.asList(8, 4, 1, 5)));
	}

	@Test
	public void shouldRemoveRoot() {
		BinaryTree<Integer> tree = new BinaryTree<Integer>(8);
		tree.add(3);
		tree.add(14);

		assertTrue(tree.remove(8));
		List<Integer> nodes = tree.preOrderTraverse();
		assertTrue(nodes.equals(Arrays.asList(14, 3)));

		assertTrue(tree.remove(14));
		nodes = tree.preOrderTraverse();
		assertTrue(nodes.equals(Arrays.asList(3)));

		assertTrue(tree.remove(3));
		assertTrue(tree.getSize() == 0);
	}

	@Test
	public void shouldRemoveAndAddNodes() {
		BinaryTree<Integer> tree = createBalancedTree();

		assertTrue(tree.remove(6));
		List<Integer> nodes = tree.preOrderTraverse();
		assertTrue(nodes.equals(Arrays.asList(10, 8, 4, 15, 13, 18)));

		assertTrue(tree.remove(15));
		nodes = tree.preOrderTraverse();
		assertTrue(nodes.equals(Arrays.asList(10, 8, 4, 18, 13)));

		assertTrue(tree.remove(8));
		nodes = tree.preOrderTraverse();
		assertTrue(nodes.equals(Arrays.asList(10, 4, 18, 13)));

		assertTrue(tree.remove(18));
		nodes = tree.preOrderTraverse();
		assertTrue(nodes.equals(Arrays.asList(10, 4, 13)));

		assertTrue(tree.remove(4));
		nodes = tree.preOrderTraverse();
		assertTrue(nodes.equals(Arrays.asList(10, 13)));

		assertTrue(tree.remove(10));
		nodes = tree.preOrderTraverse();
		assertTrue(nodes.equals(Arrays.asList(13)));

		assertTrue(tree.remove(13));
		tree.add(5);
		tree.add(4);
		tree.add(6);
		nodes = tree.preOrderTraverse();
		assertTrue(nodes.equals(Arrays.asList(5, 4, 6)));
	}

	private BinaryTree<Integer> createTree() {
		BinaryTree<Integer> tree = new BinaryTree<Integer>(5);

		tree.add(6);
		tree.add(7);
		tree.add(4);
		tree.add(1);
		tree.add(2);
		tree.add(8);
		tree.add(4);

		return tree;
	}

	private BinaryTree<Integer> createBalancedTree() {
		BinaryTree<Integer> tree = new BinaryTree<Integer>(10);

		tree.add(6);
		tree.add(4);
		tree.add(8);
		tree.add(15);
		tree.add(13);
		tree.add(18);

		return tree;

	}
}
