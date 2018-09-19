--------------------------------
----Author : Samson
----Date created : 27/03/2017
--------------------------------

local function putBin(r,name,value)
    if not aerospike:exists(r) then aerospike:create(r) end
    r[name] = value
    aerospike:update(r)
end

-- Set a particular bin
function writeBin(r,name,value,name2,value2)
    putBin(r,name,value)
    putBin(r,name2,value2)
end

-- Get a particular bin
function readBin(r,name)
    return r[name]
end

--delete a record
function deleteRecord(r)
    aerospike:remove(r)
end