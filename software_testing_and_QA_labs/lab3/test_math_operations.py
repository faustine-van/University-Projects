def add_numbers(a, b):
    return a + b

def test_add_positive():
    result = add_numbers(3, 5)
    assert result == 8

def test_add_negative():
    assert add_numbers(-1, -1) == -2