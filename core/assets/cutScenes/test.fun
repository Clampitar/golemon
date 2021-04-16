fun foo(i) do
  return 2;
end

fun bar(x) do
  return foo(3);
end

print bar(4) + 2;
println;

print "Autre resultat: ";
print foo(10) - 1;
println;
# walk (20);
frameAdvance 15;
print "delyed";
println;
var q = 120;

while(20 < q) do
q = q - 10;
frameAdvance q;
print "   "+q;
end
