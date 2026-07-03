from pytest import fixture


class Calculator():
    def add(self, a, b):
        return a + b


@fixture
def calculator():
    return Calculator()

def test_add(calculator):
    result = calculator.add(3, 5)
    assert result == 8