int foo(int i) do
  i = i + 1;
  return i + 10;
end

bool bar(int x) do
  return foo(x+3);
end

print false;
print bar(3) + 2;
println;
var q = 0;

for(q = 3;q < 10;q = q + 1) do
q = q + 1;
walk(5, 5);
frameAdvance 20;
print q;
print "  ";
end