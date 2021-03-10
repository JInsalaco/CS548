import schema namespace c = "http://cs548.stevens.edu/clinic/db" at "Assignment3-XQuery.xsd";
declare namespace pr = "http://www.example.org/clinic/providers";
declare namespace functx = "http://www.functx.com";

declare function functx:distinct-deep 
  ( $nodes as node()* )  as node()* {

    for $seq in (1 to count($nodes))
    return $nodes[$seq][not(functx:is-node-in-sequence-deep-equal(
                          .,$nodes[position() < $seq]))]
 };
 
declare function functx:is-node-in-sequence-deep-equal 
  ( $node as node()? ,
    $seq as node()* )  as xs:boolean {

   some $nodeInSeq in $seq satisfies deep-equal($nodeInSeq,$node)
 };
 
declare function local:getProviderInfo($db as element(c:clinic)) {
<pr:clinic>{
    for $p in functx:distinct-deep($db/c:patient/c:treatment/c:provider)
    return
        <pr:provider>
            <pr:name>{$p/c:name/text()}</pr:name>
            <pr:npi> {$p/c:npi/text()}</pr:npi>
                <pr:patients>{
                    for $pt in $db/c:patient
                    where $pt/c:treatment/c:provider/c:npi = $p/c:npi
                    return
                    <pr:patient>
                        <pr:patient-id>{ $pt/c:patient-id/text()}</pr:patient-id>
                        <pr:patient-name>{ $pt/c:name/text()}</pr:patient-name>
                        <pr:treatments>{
                            for $t in $db/c:patient/c:treatment
                            where $t/c:provider = $p and $t= $pt/c:treatment
                            return
                            <pr:treatment>{ $t/c:drug/text() }</pr:treatment>
                        }
                        </pr:treatments>
                    </pr:patient>
                }
                </pr:patients>
        </pr:provider>
}
</pr:clinic>
};

local:getProviderInfo(doc("sample1.xml")/c:clinic)