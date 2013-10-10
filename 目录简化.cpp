queue<string> split(string src, string p){
    	queue<string> q;
    	string::size_type plen = p.length();
    	string::size_type slen = src.length();
    	string::size_type front = 0;
    	string::size_type rear = 0;
    
    	while (rear < slen){
    		front = src.find(p.c_str(), front);
    		rear = src.find(p.c_str(), front + p.length());
    		if (rear > slen){
    			rear = slen;
    		}
    		size_t tmpstrlen = rear - front;
    		if (tmpstrlen > 1){
    			string tmpstr(src, front, tmpstrlen);
    			q.push(tmpstr);
    		}
    		front = rear;
    		rear++;
    	}
    	return q;
    }
    
    string simplifyPath(string path){
    	vector<string> v;
    	queue<string> q;
    	q = split(path, "/");
    	string retstr;
    	while(!q.empty()){
    		string tmp = q.front();
    		if("/.." == tmp){
    			if (!v.empty()){
    				v.pop_back();
    			}
    		}else if("/." != tmp){
    			v.push_back(tmp);
    		}
    		q.pop();
    	}
    	if (v.empty()){
    		return "/";
    	}
    
    	for(size_t i = 0; i < v.size(); i++){
    		retstr += v[i];
    	}
    
    	return retstr;
    }
