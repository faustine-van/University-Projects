from test_math_operations import add_numbers

def test_add_positive_n():
    result = add_numbers(10, 20)
    assert result == 30