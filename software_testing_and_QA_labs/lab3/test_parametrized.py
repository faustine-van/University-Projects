import pytest

@pytest.mark.parametrize("input,expected", [
    (1, 2),
    (2, 3),
    (3, 4),
    (4, 5),
])
def test_sample_parametrized(input, expected):
    sample_data = {
        "input": input,
        "expected": expected
    }
    assert sample_data["expected"] == sample_data["input"] + 1