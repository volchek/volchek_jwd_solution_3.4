package by.tc.module3.task3_4.list;

class ListNode<T> {
	
    private T data;
    private ListNode<T> prev;
    private ListNode<T> next;

    ListNode(T data) {
        this.data = data;
    }

    ListNode(ListNode<T> node) {
        this.data = node.data;
        this.prev = node.prev;
        this.next = node.next;
        // .data, .prev and .next are accessed directly, since access level specifiers are per-class
    }

    ListNode<T> getPrev() {
        return prev;
    }

    ListNode<T> getNext() {
        return next;
    }

    T getData() {
        return data;
    }

    void setPrev(ListNode<T> prev) {
        this.prev = prev;
    }

    void setNext(ListNode<T> next) {
        this.next = next;
    }

    void setData(T data) {
        this.data = data;
    }

}
